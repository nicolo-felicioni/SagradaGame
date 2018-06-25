package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */


import it.polimi.se2018.controller.*;
import it.polimi.se2018.controller.updater.PlayerUpdater;
import it.polimi.se2018.controller.updater.*;
import it.polimi.se2018.exceptions.*;

import java.util.*;


public class Model implements ViewUpdaterObservable {

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
        this.notifyObservers(new PlayerUpdater(player.getId(), player.getTokens(), player.isConnected()));
    }

    /**
     * Removes a player from the current player's set.
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
     * Set a player.
     * @param player the new player.
     * @throws NotPresentPlayerException if the player is not in the game.
     */
    public void setPlayer(Player player) throws NotPresentPlayerException {
        removePlayer(player);
        try {
            addPlayer(player);
        } catch (TooManyPlayersException | NotValidIdException e) {
            e.printStackTrace();
        }
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
     * Return a copy of the dice bag.
     * @return the dice bag.
     */
    public DiceBag getDiceBag() {
        return diceBag.cloneDiceBag();
    }

    /**
     * Set a dice bag.
     * @param diceBag the dice bag.
     */
    public void setDiceBag(DiceBag diceBag) {
        this.diceBag = diceBag.cloneDiceBag();
        this.notifyObservers(new DiceBagUpdater(diceBag.cloneDiceBag()));
    }

    /**
     * Return a copy of the round track.
     * @return the round track.
     */
    public RoundTrack getRoundTrack() {
        return this.roundTrack.cloneRoundTrack();
    }

    /**
     * Set a round track.
     * @param roundTrack the round track.
     */
    public void setRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack.cloneRoundTrack();
        this.notifyObservers(new RoundTrackUpdater(roundTrack.cloneRoundTrack()));
    }

    /**
     * Return a copy of the draft pool.
     * @return the draft pool.
     */
    public DraftPool getDraftPool() {
        return this.draftPool.cloneDraftPool();
    }

    /**
     * Set a draft pool.
     * @param draftPool the draft pool.
     */
    public void setDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool.cloneDraftPool();
        this.notifyObservers(new DraftPoolUpdater(draftPool.cloneDraftPool()));
    }

    /**
     * Tool card getter.
     * @param p the position of the tool card.
     * @return the tool card number in the position p.
     */
    public ToolCard getToolCard(CardPosition p){
        if(toolCards != null) {
            return this.toolCards[p.toInt()].cloneToolCard();
        }else{
            return null;
        }
    }

    /**
     * Tool card setter.
     * @param card the tool card.
     * @param p the position of the tool card.
     */
    public void setToolCard(ToolCard card, CardPosition p){
        if(toolCards != null) {
            this.toolCards[p.toInt()] = card.cloneToolCard();
        }
        this.notifyObservers(new ToolCardUpdater(card.cloneToolCard(), p));
    }

    /**
     * Tool card setter.
     * @param card the tool card.
     */
    public void setToolCard(ToolCard card){
        for(int i = 0; i < toolCards.length; i++) {
            if( toolCards[i].getName().equals(card.getName()) ) {
                toolCards[i] = card.cloneToolCard();
                this.notifyObservers(new ToolCardUpdater(card.cloneToolCard(), CardPosition.fromInt(i)));
            }
        }
    }

    /**
     * Public objective card getter.
     * @param p the position of the public objective card.
     * @return the tool card number in the position p.
     */
    public PublicObjectiveCard getPublicObjectiveCard(CardPosition p){
        if(publicObjectiveCards != null) {
            return this.publicObjectiveCards[p.toInt()];
        }else{
            return null;
        }
    }

    /**
     * Public objective  card setter.
     * @param card the public objective  card.
     * @param p the position of the public objective  card.
     * @return the tool card number in the position p.
     */
    public void setPublicObjectiveCard(PublicObjectiveCard card, CardPosition p){
        if(publicObjectiveCards != null) {
            this.publicObjectiveCards[p.toInt()] = card;
        }
        this.notifyObservers(new PublicObjectiveCardUpdater(card, p));
    }

    /**
     * Return a list of the players.
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
     * Set the window patterns to be choosen to a player.
     * @param playerId the player identifier.
     * @param windowPatterns the window patterns.
     * @throws NotValidIdException if there's no player with such a player identifer.
     * @throws NotValidPatternVectorException if the array of window pattern is not valid.
     */
    public void setWindowPatterns(String playerId, WindowPattern[] windowPatterns) throws NotValidIdException, NotValidPatternVectorException {
        Optional<Player> wantedPlayer = this.players.stream()
                .filter(player -> player.getId().equals(playerId)).findAny();

        if(!wantedPlayer.isPresent())
            throw new NotValidIdException("Wanted to get a player with a not valid id.");

        wantedPlayer.get().setPatterns(windowPatterns);
        notifyObservers(new WindowPatternUpdater(playerId, windowPatterns[WindowPatternPosition.FIRST.toInt()].cloneWindowPattern(), WindowPatternPosition.FIRST));
        notifyObservers(new WindowPatternUpdater(playerId, windowPatterns[WindowPatternPosition.SECOND.toInt()].cloneWindowPattern(), WindowPatternPosition.SECOND));
        notifyObservers(new WindowPatternUpdater(playerId, windowPatterns[WindowPatternPosition.THIRD.toInt()].cloneWindowPattern(), WindowPatternPosition.THIRD));
        notifyObservers(new WindowPatternUpdater(playerId, windowPatterns[WindowPatternPosition.FOURTH.toInt()].cloneWindowPattern(), WindowPatternPosition.FOURTH));
    }

    /**
     * Set the chosen window pattern to a player.
     * @param playerId the player identifier.
     * @param windowPattern the window pattern.
     * @throws NotValidIdException if there's no player with such a player identifer.
     * @throws NotValidPatterException if the window pattern is not valid.
     */
    public void setChosenWindowPattern(String playerId, WindowPattern windowPattern) throws NotValidIdException, NotValidPatterException {
        Optional<Player> wantedPlayer = this.players.stream()
                .filter(player -> player.getId().equals(playerId)).findAny();

        if(!wantedPlayer.isPresent())
            throw new NotValidIdException("Wanted to get a player with a not valid id.");

        wantedPlayer.get().choosePattern(windowPattern);

        notifyObservers(new WindowPatternUpdater(playerId,windowPattern.cloneWindowPattern(), WindowPatternPosition.CHOSEN));
    }

    /**
     * Set the private objective card to a player.
     * @param playerId the player identifier.
     * @param privateObjectiveCard the private objective card.
     * @throws NotValidIdException if there's no player with such a player identifer.
     */
    public void setPrivateObjectiveCard(String playerId, PrivateObjectiveCard privateObjectiveCard) throws NotValidIdException {
        Optional<Player> wantedPlayer = this.players.stream()
                .filter(player -> player.getId().equals(playerId)).findAny();

        if(!wantedPlayer.isPresent())
            throw new NotValidIdException("Wanted to get a player with a not valid id.");

        wantedPlayer.get().setPrivateObjective(privateObjectiveCard);
        notifyObservers(new PrivateObjectiveCardUpdater(playerId, privateObjectiveCard));
    }

    /**
     * Change a player state to another player state.
     * @param playerId the player identifier.
     * @param playerState the player state.
     * @throws NotValidIdException if there's no player with such a player identifer.
     */
    public void changePlayerStateTo(String playerId, PlayerState playerState) throws NotValidIdException {
        Optional<Player> wantedPlayer = this.players.stream()
                .filter(player -> player.getId().equals(playerId)).findAny();

        if(!wantedPlayer.isPresent())
            throw new NotValidIdException("Wanted to get a player with a not valid id.");

        wantedPlayer.get().changePlayerStateTo(playerState.cloneState());
        this.notifyObservers(new PlayerStateUpdater(playerId, playerState.cloneState()));
    }

    /**
     * gets all the public objective cards.
     * @return an array with all the public objective cards within.
     */
    public PublicObjectiveCard[] getPublicObjectiveCards() {
        PublicObjectiveCard[] cards = new PublicObjectiveCard[this.publicObjectiveCards.length];
        for(int i = 0; i < publicObjectiveCards.length; i++){
            cards[i] = publicObjectiveCards[i];
        }
        return cards;
    }

    /**
     * gets all the tool cards.
     * @return an array with all the tool cards within.
     */
    public ToolCard[] getToolCards() {
        ToolCard[] cards = new ToolCard[this.toolCards.length];
        for(int i = 0; i < toolCards.length; i++){
            cards[i] = toolCards[i].cloneToolCard();
        }
        return cards;
    }

    /**
     * Set the public objective cards.
     * @param cards an array of public objective cards.
     */
    public void setPublicObjectiveCards(PublicObjectiveCard[] cards) {
        PublicObjectiveCard[] publicObjectiveCards = new PublicObjectiveCard[cards.length];
        for(int i = 0; i < cards.length; i++){
            publicObjectiveCards[i] = cards[i];
        }
        this.publicObjectiveCards = publicObjectiveCards;
        this.notifyObservers(new PublicObjectiveCardUpdater(cards[CardPosition.LEFT.toInt()], CardPosition.LEFT));
        this.notifyObservers(new PublicObjectiveCardUpdater(cards[CardPosition.CENTER.toInt()], CardPosition.CENTER));
        this.notifyObservers(new PublicObjectiveCardUpdater(cards[CardPosition.RIGHT.toInt()], CardPosition.RIGHT));
    }

    /**
     * Set the tool cards.
     * @param cards an array of tool cards.
     */
    public void setToolCards(ToolCard[] cards) {
        ToolCard[] toolCards = new ToolCard[cards.length];
        for(int i = 0; i < cards.length; i++){
            toolCards[i] = cards[i];
        }
        this.toolCards = toolCards;
        this.notifyObservers(new ToolCardUpdater(cards[CardPosition.LEFT.toInt()].cloneToolCard(), CardPosition.LEFT));
        this.notifyObservers(new ToolCardUpdater(cards[CardPosition.CENTER.toInt()].cloneToolCard(), CardPosition.CENTER));
        this.notifyObservers(new ToolCardUpdater(cards[CardPosition.RIGHT.toInt()].cloneToolCard(), CardPosition.RIGHT));
    }

    /**
     * Get active tool card if any is active.
     * @return the active tool card if there's one active. Otherwise return null;
     */
    public ToolCard getActiveToolCard() {
        Optional<ToolCard> toolCard = Arrays.asList(toolCards).stream().filter(ToolCard::isActive).findAny();
        if(toolCard.isPresent()) {
            return toolCard.get().cloneToolCard();
        } else {
            return null;
        }
    }

    /**
     * Notify all observer the model state.
     */
    public void notifyModel() {
        this.notifyObservers(new ToolCardUpdater(toolCards[CardPosition.LEFT.toInt()].cloneToolCard(), CardPosition.LEFT));
        this.notifyObservers(new ToolCardUpdater(toolCards[CardPosition.CENTER.toInt()].cloneToolCard(), CardPosition.CENTER));
        this.notifyObservers(new ToolCardUpdater(toolCards[CardPosition.RIGHT.toInt()].cloneToolCard(), CardPosition.RIGHT));
        this.notifyObservers(new PublicObjectiveCardUpdater(publicObjectiveCards[CardPosition.LEFT.toInt()], CardPosition.LEFT));
        this.notifyObservers(new PublicObjectiveCardUpdater(publicObjectiveCards[CardPosition.CENTER.toInt()], CardPosition.CENTER));
        this.notifyObservers(new PublicObjectiveCardUpdater(publicObjectiveCards[CardPosition.RIGHT.toInt()], CardPosition.RIGHT));
        this.notifyObservers(new DiceBagUpdater(diceBag.cloneDiceBag()));
        this.notifyObservers(new RoundTrackUpdater(roundTrack.cloneRoundTrack()));
        this.notifyObservers(new DraftPoolUpdater(draftPool.cloneDraftPool()));
        this.players.forEach(player -> {
            notifyObservers(new WindowPatternUpdater(player.getId(), player.getPatterns()[WindowPatternPosition.FIRST.toInt()].cloneWindowPattern(), WindowPatternPosition.FIRST));
            notifyObservers(new WindowPatternUpdater(player.getId(), player.getPatterns()[WindowPatternPosition.SECOND.toInt()].cloneWindowPattern(), WindowPatternPosition.SECOND));
            notifyObservers(new WindowPatternUpdater(player.getId(), player.getPatterns()[WindowPatternPosition.THIRD.toInt()].cloneWindowPattern(), WindowPatternPosition.THIRD));
            notifyObservers(new WindowPatternUpdater(player.getId(), player.getPatterns()[WindowPatternPosition.FOURTH.toInt()].cloneWindowPattern(), WindowPatternPosition.FOURTH));
            notifyObservers(new WindowPatternUpdater(player.getId(), player.getPattern().cloneWindowPattern(), WindowPatternPosition.CHOSEN));
            notifyObservers(new PlayerStateUpdater(player.getId(), player.getState().cloneState()));
            notifyObservers(new PrivateObjectiveCardUpdater(player.getId(), player.getPrivateObjective()));
            notifyObservers(new PlayerUpdater(player.getId(), player.getTokens(), player.isConnected()));
        });
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
        this.observers.stream().forEach(observer -> observer.handle(updater));
    }

}