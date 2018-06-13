package it.polimi.se2018.controller;

import it.polimi.se2018.controller.factory.PrivateObjectiveCardsFactory;
import it.polimi.se2018.controller.factory.PublicObjectiveCardsFactory;
import it.polimi.se2018.controller.factory.ToolCardsFactory;
import it.polimi.se2018.controller.factory.WindowPatternFactory;
import it.polimi.se2018.controller.utils.Scheduler;
import it.polimi.se2018.event.*;
import it.polimi.se2018.exceptions.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.observer.GameEventObserver;

import java.util.List;

/**
 * @author davide yi xian hu
 */
public class Controller implements GameEventObserver {

	private Model model;
	private Scheduler scheduler;

	public Controller(Model model) {
		this.model = model;
	}

	/**
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void handle(ChooseDraftDieValueGameEvent event) {

	}

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void handle(DecreaseDieValueGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void handle(DraftAndPlaceAgainGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void handle(DraftAndPlaceNoAdjacentGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void handle(DraftAndPlaceGameEvent event) {

	}

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void handle(EndTurnGameEvent event) {

	}

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void handle(FlipDraftDieGameEvent event) {

	}

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void handle(IncreaseDieValueGameEvent event) {

	}

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {

	}

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {

	}

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void handle(MoveDieMatchColorRoundTrackGameEvent event) {

	}

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	@Override
	public void handle(MoveDieRespectAllRestrictionsGameEvent event) {

	}

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void handle(RerollAllDraftDiceGameEvent event) {

	}

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void handle(RerollDraftDieGameEvent event) {

	}

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {

	}

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {

	}

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void handle(UseToolCardGameEvent event) {

	}

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void handle(WindowPatternChosenGameEvent event) {
		System.out.println(" ===> Controller :: Window pattern choice received."); //TODO println
		try {
			this.model.setChosenWindowPattern(event.getPlayerId(), event.getWindow());
		} catch (NotValidPatterException e) {
			e.printStackTrace();
		} catch (NotValidIdException e) {
			e.printStackTrace();
		}
		if(this.checkAllPlayersHaveChosenWindowPattern()) {
			System.out.println(" ===> Controller :: All players have chosen a window pattern..."); //TODO println
			System.out.println(" ===> Controller :: Init public objective cards and tool cards..."); //TODO println
			this.initPublicObjectiveCards();
			this.initToolCards();
			this.firstTurn();
		}
	}

	/**
	 * Handle a StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void handle(StartGameEvent event) {
		System.out.println(" ===> Controller :: Game started. Initializing the model..."); //TODO println
		this.startGame(event.getPlayerIds());
		this.initPrivateObjectiveCards();
		this.initWindowPatterns();
		this.initPlayerState();
	}

	/**
	 * Start the game. Initialize the model with the players.
	 * @param ids a list of player identifiers.
	 */
	private void startGame(List<String> ids) {
		ids.stream().forEach(id -> {
			try {
				this.model.addPlayer(new Player(id));
			} catch (TooManyPlayersException e) {
				e.printStackTrace();
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Initialize private objective cards.
	 * Give a private objective card to each player.
	 */
	private void initPrivateObjectiveCards() {
		PrivateObjectiveCardsFactory privateCardFactory = new PrivateObjectiveCardsFactory();
		model.getPlayers().stream().forEach(player -> {
			try {
				model.setPrivateObjectiveCardToPlayer(player.getId(), privateCardFactory.drawCard());
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Initialize window patterns.
	 * Give to each player a set of window patterns.
	 */
	private void initWindowPatterns() {
		WindowPatternFactory windowPatternFactory = new WindowPatternFactory();
		model.getPlayers().stream().forEach(player -> {
			try {
				try {
					model.setPatternsToPlayer(player.getId(), windowPatternFactory.getWindowPattern(Player.N_WINDOW_PATTERNS));
				} catch (NotValidIdException e) {
					e.printStackTrace();
				}
			} catch (NotValidPatternVectorException e) {
				e.printStackTrace();
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
	 * Next turn. Change the state of the current turn's player.
	 * Wake up the next turn's player.
	 */
	private void nextTurn() {
		if (this.scheduler.getCurrentPlayerId() != null) {
			try {
				model.endTurn(this.scheduler.getCurrentPlayerId());
			} catch (GameMoveException e) {
				e.printStackTrace();
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		}
		if (this.scheduler.hasNext()) {
			try {
				model.startTurn(this.scheduler.next());
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Next turn. Wake up the next turn's player. Other players' state will be NotYourTurn.
	 */
	private void firstTurn() {
		model.getPlayersId().stream().forEach(id -> {
			try {
				model.changePlayerStateTo(id, new NotYourTurnState());
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		});
		if (this.scheduler.hasNext()) {
			try {
				model.startTurn(this.scheduler.next());
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Init the players' state.
	 * The initial state is ChooseWindowPatternState.
	 */
	private void initPlayerState() {
		model.getPlayersId().stream().forEach(id -> {
			try {
				model.changePlayerStateTo(id, new ChooseWindowPatternState());
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Check if all players have chosen a window pattern.
	 * @return true if all players have chosen a window pattern.
	 */
	private boolean checkAllPlayersHaveChosenWindowPattern(){
		for(Player p : model.getPlayers()){
			if(!p.hasChosenWindowPattern()) {
				return false;
			}
		}
		return true;
	}
}
