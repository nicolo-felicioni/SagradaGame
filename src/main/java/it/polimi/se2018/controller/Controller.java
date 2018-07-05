package it.polimi.se2018.controller;

import it.polimi.se2018.controller.factory.PrivateObjectiveCardsFactory;
import it.polimi.se2018.controller.factory.PublicObjectiveCardsFactory;
import it.polimi.se2018.controller.factory.ToolCardsFactory;
import it.polimi.se2018.controller.factory.WindowPatternFactory;
import it.polimi.se2018.controller.updater.EndGameUpdater;
import it.polimi.se2018.controller.updater.ErrorMessageUpdater;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.controller.utils.Scheduler;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.event.network.DisconnectEvent;
import it.polimi.se2018.event.network.ReconnectGameEvent;
import it.polimi.se2018.exceptions.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.ServerConfiguration;
import it.polimi.se2018.observable.network.DisconnectObservable;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.observer.game.ReconnectObserver;
import it.polimi.se2018.observer.network.DisconnectObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Davide Yi Xian Hu
 */
public class Controller implements GameEventObserver, ViewUpdaterObservable, ReconnectObserver, DisconnectObservable {

    private static final String TOOL_CARD_CONDITION_ERROR_MESSAGE = "The tool card can't make this action";

    private static final String NO_DIE_ERROR_MESSAGE = "There's no die in the initial space.";

    /**
     * The game model. It contains all the information about the game.
     */
    private Model model;

    /**
     * The game scheduler. It manages player's turns.
     */
    private Scheduler scheduler;

    /**
     * List of disconnected player identifiers.
     */
    private List<String> disconnectedPlayersId;

    /**
     * The view updater observers.
     */
    private List<ViewUpdaterObserver> observers;

    /**
     * Disconnect observers.
     */
    private List<DisconnectObserver> disconnectObservers;

    /**
     * Public constructor.
     *
     * @param model the game model.
     */
    public Controller(Model model) {
        this.model = model;
        this.disconnectedPlayersId = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.disconnectObservers = new ArrayList<>();
    }

    /**
     * Handle a choose draft die value game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions it change the value of a draft die with the a new value.
     *
     * @param event the choose draft die value game event.
     */
    @Override
    public void handle(ChooseDraftDieValueGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            if (toolCard != null && toolCard.chooseNewDieValue()) {
                toolCard.consumeEffect();
                draftPool.removeDie(event.getDraftedDie());
                draftPool.addDie(new Die(event.getDraftedDie().getColor(), event.getValue()));
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setToolCard(toolCard.cloneToolCard());
            }
        } catch (NotValidDieException | ToolCardStateException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a decrease die value game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions it decrease the value of the die.
     *
     * @param event the decrease die value game event.
     */
    @Override
    public void handle(DecreaseDieValueGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            if (toolCard != null && toolCard.decreaseDieValue()) {
                toolCard.consumeEffect();
                draftPool.removeDie(event.getDraftedDie());
                draftPool.addDie(new Die(event.getDraftedDie().getColor(), event.getDraftedDie().getValue().decreaseValue()));
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setToolCard(toolCard.cloneToolCard());
            }
        } catch (NotValidDieException | ToolCardStateException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a draft and place again game event.
     * Check if the tool card let the player play this effect and check if it is the first turn of the round.
     * After checking all conditions, let the player draft and place again after his first turn.
     * Remove the next occurrence of the player in the scheduler queue.
     *
     * @param event the draft and place again game event.
     */
    @Override
    public void handle(DraftAndPlaceAgainGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            WindowPattern windowPattern = model.getPlayer(event.getPlayerId()).getPattern();
            if (toolCard != null && toolCard.placeDieAfterFirstTurn() && scheduler.isFirstHalfOfRound() && scheduler.removeFirstOccurenceOf(event.getPlayerId())) {
                toolCard.consumeEffect();
                draftPool.removeDie(event.getDraftedDie());
                windowPattern.placeDie(event.getDraftedDie(), event.getPoint());
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setToolCard(toolCard.cloneToolCard());
                model.setWindowPattern(event.getPlayerId(), windowPattern.cloneWindowPattern());
            } else {
                if (toolCard != null && !toolCard.placeDieAfterFirstTurn())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
                else if (!scheduler.isFirstHalfOfRound())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "Can't activate this effect, it's not the first turn of your round."));

            }
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a draft and place no adjacent game event.
     * Check if the tool card let the player play this effect and the placement is legal.
     * After checking all conditions, let the player draft and place a die in a space that has no adjacent spaces with dice.
     *
     * @param event the draft and place no adjacent game event.
     */
    @Override
    public void handle(DraftAndPlaceNoAdjacentGameEvent event) {
        try {
            Player player = model.getPlayer(event.getPlayerId());
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            WindowPattern windowPattern = model.getPlayer(event.getPlayerId()).getPattern();
            PlayerState state = model.getPlayer(event.getPlayerId()).getState();
            if (toolCard != null && toolCard.placeDraftedDieNoAdjacent() && player.getState().canPlaceDie()) {
                toolCard.consumeEffect();
                draftPool.removeDie(event.getDraftedDie());
                windowPattern.placeDieIgnoreAdjacent(event.getDraftedDie(), event.getPoint());
                state.diePlaced();
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setToolCard(toolCard.cloneToolCard());
                model.setWindowPattern(event.getPlayerId(), windowPattern.cloneWindowPattern());
                model.changePlayerStateTo(event.getPlayerId(), state.cloneState());
            } else {
                if (toolCard != null && !toolCard.placeDraftedDieNoAdjacent())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
                else if (!state.canPlaceDie())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "You can't place die."));
            }
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a draft and place game event.
     * Check if the player can draft and place.
     * After checking all conditions, let the player draft and place a die in a space, respecting all restrictions.
     *
     * @param event the draft and place game event.
     */
    @Override
    public void handle(DraftAndPlaceGameEvent event) {
        try {
            Player player = model.getPlayer(event.getPlayerId());
            DraftPool draftPool = model.getDraftPool();
            WindowPattern windowPattern = player.getPattern();
            PlayerState state = player.getState();
            if (state.canPlaceDie()) {
                draftPool.removeDie(event.getDraftedDie());
                windowPattern.placeDie(event.getDraftedDie(), event.getPoint());
                state.diePlaced();
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setWindowPattern(event.getPlayerId(), windowPattern.cloneWindowPattern());
                model.changePlayerStateTo(event.getPlayerId(), state.cloneState());
            } else if (!state.canPlaceDie())
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "You can't place die."));
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle an end turn event.
     * Check if the player can end the turn.
     * After checking all conditions, let the player and go to next turn.
     *
     * @param event the end turn event.
     */
    @Override
    public void handle(EndTurnGameEvent event) {
        this.nextTurn();
    }

    /**
     * Handle a flip drafted die game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions, let the player flip a die in the draft pool.
     *
     * @param event the flip drafted die game event.
     */
    @Override
    public void handle(FlipDraftDieGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            if (toolCard != null && toolCard.flipDraftedDie()) {
                draftPool.removeDie(event.getDraftedDie());
                draftPool.addDie(event.getDraftedDie().flip());
                toolCard.consumeEffect();
                model.setToolCard(toolCard.cloneToolCard());
                model.setDraftPool(draftPool.cloneDraftPool());
            } else
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
        } catch (NotValidDieException | ToolCardStateException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a decrease die value game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions it increase the value of the die.
     *
     * @param event the increase die value game event.
     */
    @Override
    public void handle(IncreaseDieValueGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            if (toolCard != null && toolCard.decreaseDieValue()) {
                toolCard.consumeEffect();
                draftPool.removeDie(event.getDraftedDie());
                draftPool.addDie(new Die(event.getDraftedDie().getColor(), event.getDraftedDie().getValue().increaseValue()));
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setToolCard(toolCard);
            } else
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
        } catch (NotValidDieException | ToolCardStateException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a move die ignore color restriction event.
     * Check if the tool card let the player play this effect and check if the move is legal.
     * After checking all conditions move the die ignoring the space color restriction.
     *
     * @param event the move die ignore color restriction event.
     */
    @Override
    public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            WindowPattern windowPattern = model.getPlayer(event.getPlayerId()).getPattern();
            if (toolCard != null && toolCard.moveDieIgnoreColor() && windowPattern.getSpace(event.getInitialPosition()).hasDie()) {
                toolCard.consumeEffect();
                Die die = windowPattern.getSpace(event.getInitialPosition()).getDie();
                windowPattern.removeDie(event.getInitialPosition());
                windowPattern.placeDieIgnoreColor(die, event.getFinalPosition());
                model.setToolCard(toolCard);
                model.setWindowPattern(event.getPlayerId(), windowPattern.cloneWindowPattern());
            } else {
                if (toolCard != null && !toolCard.moveDieIgnoreColor())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
                else if (!windowPattern.getSpace(event.getInitialPosition()).hasDie())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), NO_DIE_ERROR_MESSAGE));
            }
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a move die ignore value restriction event.
     * Check if the tool card let the player play this effect and check if the move is legal.
     * After checking all conditions move the die ignoring the space value restriction.
     *
     * @param event the move die ignore value restriction event.
     */
    @Override
    public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            WindowPattern windowPattern = model.getPlayer(event.getPlayerId()).getPattern();
            if (toolCard != null && toolCard.moveDieIgnoreValue() && windowPattern.getSpace(event.getInitialPosition()).hasDie()) {
                toolCard.consumeEffect();
                Die die = windowPattern.getSpace(event.getInitialPosition()).getDie();
                windowPattern.removeDie(event.getInitialPosition());
                windowPattern.placeDieIgnoreValue(die, event.getFinalPosition());
                model.setToolCard(toolCard);
                model.setWindowPattern(event.getPlayerId(), windowPattern.cloneWindowPattern());
            } else {
                if (!windowPattern.getSpace(event.getInitialPosition()).hasDie())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), NO_DIE_ERROR_MESSAGE));
                else
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
            }

        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a move die that match the color of a die in the round track event.
     * Check if the tool card let the player play this effect, check if the move is legal and check if the selected die match the color of a die in the round track.
     * After checking all conditions move the die.
     *
     * @param event the move die that match the color of a die in the round track event.
     */
    @Override
    public void handle(MoveDieMatchColorRoundTrackGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            WindowPattern windowPattern = model.getPlayer(event.getPlayerId()).getPattern();
            if (toolCard != null && toolCard.moveTwoDiceMatchColorOnRoundTrack() && windowPattern.getSpace(event.getInitialPosition()).hasDie()) {
                toolCard.consumeEffect();
                Die die = windowPattern.getSpace(event.getInitialPosition()).getDie();

                if (model.getRoundTrack().getAllDice().stream().anyMatch(d -> d.getColor().equals(die.getColor()))) {
                    windowPattern.removeDie(event.getInitialPosition());
                    windowPattern.placeDie(die, event.getFinalPosition());
                    model.setToolCard(toolCard);
                    model.setWindowPattern(event.getPlayerId(), windowPattern.cloneWindowPattern());

                } else {
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "Die not matching round track color."));
                }
            } else {
                if (!windowPattern.getSpace(event.getInitialPosition()).hasDie())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), NO_DIE_ERROR_MESSAGE));
                else
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
            }
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a move die respect all restriction event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions move the die.
     *
     * @param event the move die respect all restriction event.
     */
    @Override
    public void handle(MoveDieRespectAllRestrictionsGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            WindowPattern windowPattern = model.getPlayer(event.getPlayerId()).getPattern();
            if (toolCard != null && toolCard.moveADie() && windowPattern.getSpace(event.getInitialPosition()).hasDie()) {
                toolCard.consumeEffect();
                Die die = windowPattern.getSpace(event.getInitialPosition()).getDie();
                windowPattern.removeDie(event.getInitialPosition());
                windowPattern.placeDie(die.cloneDie(), event.getFinalPosition());
                model.setToolCard(toolCard);
                model.setWindowPattern(event.getPlayerId(), windowPattern.cloneWindowPattern());

            } else {
                if (!windowPattern.getSpace(event.getInitialPosition()).hasDie())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), NO_DIE_ERROR_MESSAGE));
                else
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
            }

        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a reroll all draft pool dice game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions reroll every dice im the draft pool.
     *
     * @param event the reroll all draft pool dice game event.
     */
    @Override
    public void handle(RerollAllDraftDiceGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            Player player = model.getPlayer(scheduler.getCurrentPlayerId());
            if (toolCard != null && toolCard.rerollAllDraftPoolDice() && !scheduler.isFirstHalfOfRound() && !player.getState().isDiePlaced()) {
                toolCard.consumeEffect();
                draftPool.rollAllDice();
                model.setToolCard(toolCard);
                model.setDraftPool(draftPool);
            } else {
                if (scheduler.isFirstHalfOfRound())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "It's not your second turn."));
                else if (player.getState().isDiePlaced())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "Can't activate this tool card because you have already placed a die."));
                else if (toolCard != null && !toolCard.rerollAllDraftPoolDice())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
            }
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a reroll draft die game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions reroll the die in the draft pool.
     *
     * @param event the reroll draft die game event.
     */
    @Override
    public void handle(RerollDraftDieGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            Die die = event.getDraftedDie();
            if (toolCard != null && toolCard.rerollDraftedDie()) {
                toolCard.consumeEffect();
                draftPool.removeDie(die.cloneDie());
                die.roll();
                draftPool.addDie(die.cloneDie());
                model.setToolCard(toolCard.cloneToolCard());
                model.setDraftPool(draftPool.cloneDraftPool());
            } else
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a swap draft pool die with a dice bag die game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions swap the dice.
     *
     * @param event the swap draft pool die with a dice bag die game event.
     */
    @Override
    public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            DiceBag diceBag = model.getDiceBag();
            if (toolCard != null && toolCard.returnDieAndGetNewFromDiceBag()) {
                toolCard.consumeEffect();
                draftPool.removeDie(event.getDraftedDie());
                diceBag.addDie((event.getDraftedDie().cloneDie()));
                Die diceBagDie = diceBag.drawDie().cloneDie();
                draftPool.setDraftedDie(diceBagDie.cloneDie());
                draftPool.addDie(diceBagDie.cloneDie());
                model.setToolCard(toolCard.cloneToolCard());
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setDiceBag(diceBag.cloneDiceBag());
            } else
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a swap draft pool die with a round track die game event.
     * Check if the tool card let the player play this effect.
     * After checking all conditions swap the dice.
     *
     * @param event the swap draft pool die with a round track die game event.
     */
    @Override
    public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {
        try {
            ToolCard toolCard = model.getActiveToolCard();
            DraftPool draftPool = model.getDraftPool();
            RoundTrack roundTrack = model.getRoundTrack();
            if (toolCard != null && toolCard.swapDraftDieWithRoundTrackDie()) {
                toolCard.consumeEffect();
                draftPool.removeDie(event.getDraftedDie());
                roundTrack.remove(event.getRoundTrackDie(), event.getRound());
                draftPool.addDie(event.getRoundTrackDie());
                roundTrack.addDie(event.getDraftedDie(), event.getRound());
                model.setToolCard(toolCard.cloneToolCard());
                model.setDraftPool(draftPool.cloneDraftPool());
                model.setRoundTrack(roundTrack.cloneRoundTrack());
            } else
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), TOOL_CARD_CONDITION_ERROR_MESSAGE));
        } catch (GameMoveException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a use tool card event.
     * Check if the player can use a tool card and check if he has enough favor tokens.
     * After checking all conditions, activate the tool card and spend player's favor tokens.
     *
     * @param event the use tool card event.
     */
    @Override
    public void handle(UseToolCardGameEvent event) {
        try {
            Player player = model.getPlayer(event.getPlayerId());
            ToolCard toolCard = model.getToolCard(event.getPositionOfToolCard());
            PlayerState state = player.getState();
            if (state.canUseTool() && player.getTokens() >= toolCard.cost()) {
                player.spendToken(toolCard.cost());
                toolCard.activate();
                state.useTool();
                model.setPlayer(player);
                model.changePlayerStateTo(player.getId(), state);
                model.setToolCard(toolCard.cloneToolCard(), event.getPositionOfToolCard());
            } else {
                if (player.getTokens() < toolCard.cost())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "You haven't enough tokens."));
                else if (!state.canUseTool())
                    this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), "You can't use a tool card"));
            }

        } catch (GameMoveException | NotPresentPlayerException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Handle a window pattern chosen game event.
     * Check if the player is in the choosing window pattern state.
     * After checking all conditions, set the pattern as the chosen one.
     *
     * @param event the window pattern chosen game event.
     */
    @Override
    public void handle(WindowPatternChosenGameEvent event) {
        try {
            if (!model.getPlayer(event.getPlayerId()).getState().hasChosenWindowPattern()) {
                model.setChosenWindowPattern(event.getPlayerId(), event.getWindow());
            }
        } catch (NotValidPatterException | NotValidIdException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
        if (this.checkAllPlayersHaveChosenWindowPattern()) {
            this.initPublicObjectiveCards();
            this.initToolCards();
            this.firstTurn();
        }
    }

    /**
     * Handle a start game event.
     *
     * @param event the start game event.
     */
    @Override
    public void handle(StartGameEvent event) {
        this.startGame(event.getPlayerIds());
        this.initScheduler();
        this.initPrivateObjectiveCards();
        this.initWindowPatterns();
        this.initPlayerState();
    }


    /**
     * Handle a reconnect event.
     *
     * @param event the reconnect event.
     */
    @Override
    public void handle(ReconnectGameEvent event) {
        try {
            String id = event.getPlayerId();
            if (disconnectedPlayersId.stream().anyMatch(s -> s.equals(id))) {
                this.disconnectedPlayersId.remove(id);
                Player player = model.getPlayer(id);
                player.setConnected(true);
                model.setPlayer(player);
                model.notifyModel();
            }
        } catch (NotValidIdException | NotPresentPlayerException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * Start the game. Initialize the model with the players.
     *
     * @param ids a list of player identifiers.
     */
    private void startGame(List<String> ids) {
        ids.forEach(id -> {
            try {
                this.model.addPlayer(new Player(id));
            } catch (TooManyPlayersException | NotValidIdException e) {
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
            }
        });
    }

    /**
     * End the game.
     */
    private void endGame() {
        List<RankingPlayer> players = sortPlayers();
        this.notifyObservers(new EndGameUpdater(players, disconnectedPlayersId));
        this.model.getPlayersId().forEach(id -> this.notifyObservers(new DisconnectEvent(id)));
    }

    /**
     * Initialize private objective cards.
     * Give a private objective card to each player.
     */
    private void initPrivateObjectiveCards() {
        PrivateObjectiveCardsFactory privateCardFactory = new PrivateObjectiveCardsFactory();
        model.getPlayersId().forEach(id -> {
            try {
                model.setPrivateObjectiveCard(id, privateCardFactory.drawCard());
            } catch (NotValidIdException e) {
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
            }
        });
    }

    /**
     * Initialize window patterns.
     * Give to each player a set of window patterns.
     */
    private void initWindowPatterns() {
        WindowPatternFactory windowPatternFactory = new WindowPatternFactory();
        model.getPlayersId().forEach(id -> {
            try {
                model.setWindowPatterns(id, windowPatternFactory.getWindowPattern(Player.N_WINDOW_PATTERNS));
            } catch (NotValidPatternVectorException | NotValidIdException e) {
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
            }
        });
    }

    /**
     * Initialize tool cards.
     * Draw a set of tool cards.
     */
    private void initToolCards() {
        ToolCardsFactory factory = new ToolCardsFactory();
        this.model.setToolCards(factory.drawCard(Model.SET_OF_TOOL_CARDS_SIZE));
    }

    /**
     * Initialize public objective cards.
     * Draw a set of public objective cards.
     */
    private void initPublicObjectiveCards() {
        PublicObjectiveCardsFactory factory = new PublicObjectiveCardsFactory();
        this.model.setPublicObjectiveCards(factory.drawCard(Model.SET_OF_PUBLIC_OBJECTIVE_CARDS_SIZE));
    }

    /**
     * Initialize the scheduler;
     */
    private void initScheduler() {
        this.scheduler = new Scheduler(this.model.getPlayersId());
    }

    /**
     * Go to next turn. End game if there is no player in the schedule.
     * The current playing player change his state to NotYourTurnState();
     * If there's a player, check if he is connected or not. If he is not connected, advance to next turn (recursive call).
     * If there's a player and he is connected change his state to FirstTurnState or YourTurnState.
     * It depends on if it is the first turn of the game or not.
     */
    private void nextTurn() {
        if (this.scheduler.hasNext()) {
            try {
                model.changePlayerStateTo(scheduler.getCurrentPlayerId(), new NotYourTurnState());
                String playerId = scheduler.next();
                if (disconnectedPlayersId.contains(playerId)) {
                    this.nextTurn();
                } else if (scheduler.isFirstTurnOfPlayer()) {
                    model.changePlayerStateTo(playerId, new FirstTurnState());
                    startTurnTimer();
                } else {
                    model.changePlayerStateTo(playerId, new YourTurnState());
                    startTurnTimer();
                }
                if (scheduler.isFirstTurnOfRound()) {
                    endRound();
                    initRound();
                }
                ToolCard toolCard = model.getActiveToolCard();
                if (toolCard != null) {
                    toolCard.endActivation();
                    model.setToolCard(toolCard);
                }
            } catch (NotValidIdException | ToolCardStateException e) {
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
            }
        } else {
            endGame();
        }
    }

    /**
     * First turn. Wake up the next turn's player. Other players' state will be NotYourTurn.
     */
    private void firstTurn() {
        if (this.scheduler.hasNext()) {
            try {
                String playerId = scheduler.next();
                model.getPlayers().stream().map(Player::getId).forEach(id -> {
                    try {
                        if (!id.equals(playerId))
                            model.changePlayerStateTo(id, new NotYourTurnState());
                    } catch (NotValidIdException e) {
                        this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
                    }
                });
                if (scheduler.isFirstTurnOfPlayer()) {
                    model.changePlayerStateTo(playerId, new FirstTurnState());
                    startTurnTimer();
                } else {
                    model.changePlayerStateTo(playerId, new YourTurnState());
                    startTurnTimer();
                }
                initRound();
            } catch (NotValidIdException e) {
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
            }
        } else {
            endGame();
        }
    }

    /**
     * Init the round. Move 2n+1 dice from the dice bag to the draft pool. (n is the number of the player in the game)
     */
    private void initRound() {
        try {
            DraftPool draftPool = new DraftPool();
            DiceBag diceBag = model.getDiceBag();
            draftPool.addDice(diceBag.drawDice(model.getPlayers().size() * 2 + 1));
            model.setDraftPool(draftPool);
            model.setDiceBag(diceBag);
        } catch (DiceBagException e) {
            this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
        }
    }

    /**
     * End the round. Move all the remaining dice in the draft pool to the roundTrack.
     */
    private void endRound() {
        DraftPool draftPool = model.getDraftPool();
        RoundTrack roundTrack = model.getRoundTrack();
        roundTrack.addDice(draftPool.getAllDice());
        model.setRoundTrack(roundTrack);
    }

    /**
     * This method calculates the points of each player, following Sagrada's rules.
     *
     * @return a list of players ordered following Sagrada's rules
     */
    private List<RankingPlayer> sortPlayers() {
        List<Player> players = model.getPlayers();
        List<RankingPlayer> rankingPlayers = new ArrayList<>();

        players.stream().filter(Player::isConnected).forEach(player -> {
            WindowPattern pattern = player.getPattern();
            int numberOfEmptySpaces =
                    (WindowPattern.SPACES_HEIGHT * WindowPattern.SPACES_LENGTH) - pattern.getNumberOfDice();

            int points;
            int favorTokens = player.getTokens();
            int pointsFromPrivateObjective = player.getPrivateObjective().calculatePoints(pattern);
            int pointsFromPublicObjective = Arrays.stream(model.getPublicObjectiveCards())
                    .mapToInt(card -> card.calculatePoints(pattern)).sum();
            points = favorTokens + pointsFromPrivateObjective + pointsFromPublicObjective - numberOfEmptySpaces;

            rankingPlayers.add(new RankingPlayer(player.getId(), points, pointsFromPrivateObjective, favorTokens));
        });

        rankingPlayers.sort(new RankingPlayer.RankingPlayerComparator().reversed());

        return rankingPlayers;
    }

    /**
     * Init the players' state.
     * The initial state is ChooseWindowPatternState.
     */
    private void initPlayerState() {
        model.getPlayersId().forEach(id -> {
            try {
                model.changePlayerStateTo(id, new ChooseWindowPatternState());
            } catch (NotValidIdException e) {
                this.notifyObservers(new ErrorMessageUpdater(scheduler.getCurrentPlayerId(), e.getMessage()));
            }
        });
    }

    /**
     * Check if all players have chosen a window pattern.
     *
     * @return true if all players have chosen a window pattern.
     */
    private boolean checkAllPlayersHaveChosenWindowPattern() {
        for (Player p : model.getPlayers()) {
            if (!p.hasChosenWindowPattern()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Start the timer. If the player do not end the turn before the timer countdown, it will disconnect.
     */
    private void startTurnTimer() {
        String currentTurnId = scheduler.getTurnId();
        String currentPlayerId = scheduler.getCurrentPlayerId();
        new Thread(() -> {
            try {
                Thread.sleep(ServerConfiguration.getTurnTimer());
                if (currentTurnId.equals(scheduler.getTurnId())) {
                    disconnectPlayer(currentPlayerId);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (NotValidIdException | NotPresentPlayerException e) {
                MyLog.getMyLog().log(Level.WARNING,e.getMessage());
            }
        }).start();
    }

    /**
     * Disconnect a player.
     *
     * @param id the player identifier.
     */
    private void disconnectPlayer(String id) throws NotValidIdException, NotPresentPlayerException {
        this.disconnectedPlayersId.add(id);
        Player player = model.getPlayer(id);
        player.setConnected(false);
        model.setPlayer(player);
        if (model.getPlayers().stream().filter(Player::isConnected).count() > 1) {
            this.nextTurn();
        } else {
            this.endGame();
        }
    }

    /**
     * Add a view updater observer.
     *
     * @param observer the view updater observer.
     */
    @Override
    public void addObserver(ViewUpdaterObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Remove a view updater observer.
     *
     * @param observer the view updater observer.
     */
    @Override
    public void removeObserver(ViewUpdaterObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify a view updater.
     *
     * @param updater the view updater to be executed.
     */
    @Override
    public void notifyObservers(ViewUpdaterInterface updater) {
        this.observers.forEach(o -> o.handle(updater));
    }

    /**
     * Add a DisconnectObserver.
     *
     * @param observer the DisconnectObserver.
     */
    @Override
    public void addObserver(DisconnectObserver observer) {
        this.disconnectObservers.add(observer);
    }

    /**
     * Remove a DisconnectObserver.
     *
     * @param observer the DisconnectObserver.
     */
    @Override
    public void removeObserver(DisconnectObserver observer) {
        this.disconnectObservers.remove(observer);
    }

    /**
     * Notify the DisconnectObserver an DisconnectObserver.
     *
     * @param event the DisconnectEvent.
     */
    @Override
    public void notifyObservers(DisconnectEvent event) {
        this.disconnectObservers.forEach(o -> o.handle(event));
    }
}
