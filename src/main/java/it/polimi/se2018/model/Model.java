package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */


import it.polimi.se2018.controller.*;
import it.polimi.se2018.controller.updater.PlayerUpdater;
import it.polimi.se2018.controller.updater.*;
import it.polimi.se2018.exceptions.*;
import it.polimi.se2018.view.cli.Option;

import java.util.*;


public class Model implements ModelInterface, ViewUpdaterObservable {

    private ArrayList<Player> players;
    private DiceBag diceBag;
    private DraftPool draftPool;
    private PublicObjectiveCard[] publicObjectiveCards;
    private ToolCard[] toolCards;
    private RoundTrack roundTrack;


    /**
     * View updater observers.
     */
    private List<ViewUpdaterObserver> observers;

    /**
     * Maximum amount of player.
     */
    public static final int MAX_NUMBER_OF_PLAYERS = 4;

    /**
     * The amount of cards in a set of tool cards.
     */
    public static final int SET_OF_TOOL_CARDS_SIZE = 3;

    /**
     * The amount of cards in a set of public objective cards.
     */
    public static final int SET_OF_PUBLIC_OBJECTIVE_CARDS_SIZE = 3;

    /**
     * The max round.
     */
    public static final int MAX_ROUND = 10;

    /**
     * Constructor of the class.
     *
     */
    public Model(){
        players = new ArrayList<>();
        diceBag = new DiceBag();
        draftPool = new DraftPool();
        toolCards = new ToolCard[SET_OF_TOOL_CARDS_SIZE];
        roundTrack = new RoundTrack();
        observers = new ArrayList<>();
    }

    /**
     * Adds a player to the current player's set.
     * @param player the player to be added
     * @throws TooManyPlayersException if there are already the maximum number of players.
     * @throws NotValidIdException if there is already a player with the same id of the player who should be added
     *
     */
    public void addPlayer(Player player) throws TooManyPlayersException, NotValidIdException {
        if(this.players.size()==MAX_NUMBER_OF_PLAYERS)
            throw new TooManyPlayersException("Too many players present in this game.");

        if(this.players.stream().anyMatch(presentPlayer -> presentPlayer.equalsPlayer(player)))
            throw new NotValidIdException("there is already a player with the id: " + player.getId());


        this.players.add(player);
        this.notify(new PlayerUpdater(player.getId()));
    }

    /**
     *Removes a player from the current player's set.
     * @param player player you want to remove
     * @throws NotPresentPlayerException if the player is not present
     */
    public void removePlayer(Player player) throws NotPresentPlayerException {
        for(int i=0; i<this.players.size(); i++){
            if(this.players.get(i).equalsPlayer(player)){
                this.players.remove(i);
                return;
            }
        }

        throw new NotPresentPlayerException("Player" + player + "is not present in the game.");

    }

    /**
     * Gets a player by his id.
     * The id of a player is of type String and it is unique.
     * @param playerId a string containing the id of the wanted player
     * @return the correspondent player
     * @throws NotValidIdException if there isn't any player with the given id.
     */
    public Player getPlayer(String playerId) throws NotValidIdException {

        Optional<Player> wantedPlayer = this.players.stream()
                .filter(player -> player.getId().equals(playerId)).findAny();

        if(!wantedPlayer.isPresent())
            throw new NotValidIdException("Wanted to get a player with a not valid id.");

        return new Player(wantedPlayer.get());
    }

    /**
     * gets the dice bag.
     * @return the dice bag.
     */
    public DiceBag getDiceBag() {

        //copy of the dice bag
        return new DiceBag(this.diceBag);
    }

    /**
     * gets the round track.
     * @return the round track.
     */
    public RoundTrack getRoundTrack() {
        return this.roundTrack.cloneRoundTrack();
    }

    /**
     * gets the draft pool.
     * @return the draft pool.
     */
    public DraftPool getDraftPool() {
        return this.draftPool.cloneDraftPool();
    }


    /**
     * Tool card getter.
     * n is the number of which of the 3 tool card you want to get.
     * @param n the number of which tool card you want to get
     * @return the tool card number n
     */
    public ToolCard getToolCard(int n) throws GameException{
        if(n < 0 || n > toolCards.length)
            throw new GameException("The card number :" + n + "doesn't exists.");

        return this.toolCards[n].cloneToolCard();
    }



    /**
     * Public objective card getter.
     * n is the number of which of the 3 public objective card you want to get.
     * @param n the number of which tool card you want to get
     * @return the tool card number n
     */
    public PublicObjectiveCard getPublicObjectiveCard(int n) throws GameException {
        if(n < 0 || n > publicObjectiveCards.length)
            throw new GameException("The card number :" + n + "doesn't exists.");

        return this.publicObjectiveCards[n];
    }


    /**
     * gets the players in the game.
     * @return a list of players which are in the game.
     */
    public List<Player> getPlayers() {
        List<Player> copyList = new ArrayList<>(this.players.size());

        for(Player player : this.players){
            Player copiedPlayer = new Player(player);
            copyList.add(copiedPlayer);
        }

        return copyList;
    }

    /**
     * Get the player's identifiers.
     * @return a list of players identifiers.
     */
    public List<String> getPlayersId() {
        List<String> copyList = new ArrayList<>();
        for(Player player : this.players){
            copyList.add(player.getId());
        }
        return copyList;
    }

    /**
     * gets all the public objective cards.
     * @return an array with all the public objective cards within.
     */
    public PublicObjectiveCard[] getPublicObjectiveCards() {
        return Arrays.copyOf(this.publicObjectiveCards, this.publicObjectiveCards.length);
    }

    /**
     * gets all the tool cards.
     * @return an array with all the tool cards within.
     */
    public ToolCard[] getToolCards() {
        return Arrays.copyOf(this.toolCards, this.toolCards.length);
    }


    /**
     * Place a die in the space p of the window pattern pertaining to a player identified by the player id.
     * @param p the point in which you want to place the die
     * @param die the die you want to place
     * @param playerId the id of the player
     * @throws GameException if the die placement fails.
     */
    public void placeDie(Point p, Die die, String playerId) throws GameException {

        for(Player player : this.players){
            if(player.getId().equals(playerId)){
                player.placeDie(p, die);
                return;
            }
        }

        throw new NotValidIdException("The id: " + playerId + " is not valid.");
    }


    /**
     * Place a die in the space p of the window pattern pertaining to a player.
     * @param p the point in which you want to place the die
     * @param die the die you want to place
     * @param player the player who wants to place the die
     * @throws GameException if the die placement fails.
     */
    public void placeDie(Point p, Die die, Player player) throws GameException {
        for(Player tempPlayer : this.players){
            if(tempPlayer.equalsPlayer(player)){
                tempPlayer.placeDie(p, die);
                return;
            }
        }
        throw new NotValidPlayerException("The player :" + player.getId() +  "is not present.");
    }

    /**
     * Return if a die can be placed in a space of the chosen window pattern respecting all restrictions.
     * @param p the position of the space in the window pattern.
     * @param die the die.
     * @param id the player identifier.
     * @return true if a die can be placed in a space of the chosen window pattern respecting all restrictions.
     */
    public boolean isPlaceable(Point p, Die die, String id) throws NotValidIdException {
        return getPlayerById(id).isPlaceable(p, die);
    }

    /**
     * get the number of favor tokens of the player identified  by a playerId.
     * @param playerId the unique id of the player
     * @throws GameException if the id is not valid.
     */
    public int getPlayerTokens(String playerId) throws GameException {
        Optional<Player> p = this.players.stream().filter(player -> player.getId().equals(playerId)).findAny();
        if(p.isPresent())
            return p.get().getTokens();

        throw new NotValidIdException("The id: " + playerId + " is not valid.");
    }

    /**
     *
     * @param die
     */
    public void removeDieFromDraftPool(Die die) throws NotValidDieException {
        draftPool.removeDie(die);
        this.notify(new DraftPoolUpdater(draftPool));
    }


    /**
     * Method that draws a quantity of dice depending on the number of players of the game.
     * The number of dice is the number of players multiplied by 2, all plus one.
     * @return a list of dice
     * @throws DiceBagException if the dice bag hasn't enough dice
     */

    public List<Die> drawDiceFromDiceBag() throws DiceBagException {
        return diceBag.drawDice(players.size() * 2 + 1);
    }

    /**
     * Activate the tool card in position n.
     * @param n the position of the tool card.
     * @throws ToolCardStateException if the tool card is already activated or it can not be activated.
     */

    public void activateToolCard(int n) throws ToolCardStateException {
        this.toolCards[n].activate();
    }

    /**
     * Spend player's favor tokens.
     * @param amount the amount that have to be spent.
     * @param id the player identifier.
     * @throws NotEnoughTokenException if player has not enough favor tokens.
     */
    public void spendToken(int amount, String id) throws NotEnoughTokenException, NotValidIdException {
        getPlayerById(id).spendToken(amount);
    }

    /**
     * Set a private objective card to a player.
     * @param id the player identifier.
     * @param card the private objective card to be set.
     */
    public void setPrivateObjectiveCardToPlayer (String id, PrivateObjectiveCard card) throws NotValidIdException {
        this.getPlayerById(id).setPrivateObjective(card);
        this.notify(new PrivateObjectiveCardUpdater(id, card));
    }

    /**
     * This is a setter for the 4 patterns a player can choose before starting the game.
     * @param patterns a vector formed of four window pattern
     * @param id the player identifier.
     */
    public void setPatternsToPlayer(String id, WindowPattern[] patterns) throws NotValidPatternVectorException, NotValidIdException {
        this.getPlayerById(id).setPatterns(patterns);
        this.notify(new WindowPatternUpdater(id, patterns[WindowPatternPosition.FIRST.toInt()], WindowPatternPosition.FIRST));
        this.notify(new WindowPatternUpdater(id, patterns[WindowPatternPosition.SECOND.toInt()], WindowPatternPosition.SECOND));
        this.notify(new WindowPatternUpdater(id, patterns[WindowPatternPosition.THIRD.toInt()], WindowPatternPosition.THIRD));
        this.notify(new WindowPatternUpdater(id, patterns[WindowPatternPosition.FOURTH.toInt()], WindowPatternPosition.FOURTH));
    }

    /**
     * Set the public objective cards.
     * @param cards an array of public objective cards.
     */
    public void setPublicObjectiveCards(PublicObjectiveCard[] cards) {
        this.publicObjectiveCards = cards;
    }

    /**
     * Set the tool cards.
     * @param cards an array of tool cards.
     */
    public void setToolCards(ToolCard[] cards) {
        this.toolCards = cards;
        this.notify(new ToolCardUpdater(cards[CardPosition.LEFT.toInt()], CardPosition.LEFT));
        this.notify(new ToolCardUpdater(cards[CardPosition.CENTER.toInt()], CardPosition.CENTER));
        this.notify(new ToolCardUpdater(cards[CardPosition.RIGHT.toInt()], CardPosition.RIGHT));
    }

    /**
     * End the turn of the player.
     * @param playerId the player identifier.
     * @throws GameMoveException if the player can not end his turn.
     */
    public void endTurn(String playerId) throws GameMoveException, NotValidIdException {
        Player player = this.getPlayerById(playerId);
        player.endTurn();
        this.notify(new PlayerStateUpdater(playerId, player.getState()));
    }

    /**
     * Start the turn of the player.
     * @param playerId the player identifier.
     */
    public void startTurn(String playerId) throws NotValidIdException {
        Player player = this.getPlayerById(playerId);
        player.startTurn();
        this.notify(new PlayerStateUpdater(playerId, player.getState()));
    }

    /**
     * Change the state of a player.
     * @param playerId the player identifier.
     */
    public void changePlayerStateTo(String playerId, PlayerState state) throws NotValidIdException {
        this.getPlayerById(playerId).changePlayerStateTo(state);
        this.notify(new PlayerStateUpdater(playerId, state));
    }

    /**
     * Set a window pattern as the player chosen one.
     * @param playerId the player identifier.
     * @param windowPattern the window pattern the player chose.
     * @throws NotValidPatterException if the window pattern is not valid.
     */
    public void setChosenWindowPattern(String playerId, WindowPattern windowPattern) throws NotValidPatterException, NotValidIdException {
        this.getPlayerById(playerId).choosePattern(windowPattern);
        this.notify(new WindowPatternUpdater(playerId, windowPattern));
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
    public void notify(ViewUpdaterInterface updater) {
        this.observers.stream().forEach(observer -> observer.handle(updater));
    }

    /**
     * Player getter.
     * @param id the player identifer;
     * @return the player.
     */
    private Player getPlayerById(String id) throws NotValidIdException{
        Player returnedPlayer = this.players.stream().filter(p -> p.getId().equals(id)).findAny().get();
        if(returnedPlayer == null) {
            throw new NotValidIdException(id);
        }
        return returnedPlayer;
    }
}