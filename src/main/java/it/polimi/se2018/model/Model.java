package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.*;

import java.util.*;


public class Model implements PlayerStateObservable, RoundTrackObservable, ToolCardObservable, DraftPoolObservable, WindowPatternObservable {

    private ArrayList<Player> players;
    private DiceBag diceBag;
    private DraftPool draftPool;
    private PublicObjectiveCard[] publicObjectiveCards;
    private ToolCard[] toolCards;
    private RoundTrack roundTrack;

    private final static int MAX_NUMBER_OF_PLAYERS = 4;


    /**
     * Constructor of the class.
     *
     */
    public Model(){
        players = new ArrayList<>();
        diceBag = new DiceBag();
        draftPool = new DraftPool();
        toolCards = new ToolCard[3];
        roundTrack = new RoundTrack();
    }

    /**
     * Adds a player to the current player's set.
     * @param player the player to be added
     * @throws TooManyPlayersException if there are already the maximum number of players.
     */
    public void addPlayer(Player player) throws TooManyPlayersException {
        if(this.players.size()==MAX_NUMBER_OF_PLAYERS)
            throw new TooManyPlayersException("Too many players present in this game.");

        this.players.add(player);
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

        Optional<Player> wantedPlayer = this.players.stream().filter(player -> player.getId().equals(playerId)).findAny();

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
        //TODO - manca il clone della draft pool
        throw new UnsupportedOperationException();
    }


    //che vuol dire?
    public ToolCard getToolCard() {
        // TODO - non so che vuol dire
        throw new UnsupportedOperationException();
    }



    /**
     *
     * @param n
     */
    public PublicObjectiveCard getPublicObjectiveCard(int n) {
        // TODO - implement it.polimi.se2018.model.Model.getPublicObjectiveCard
        throw new UnsupportedOperationException();
    }


    /**
     * gets the players in the game.
     * @return a list of players which are in the game.
     */
    public List<Player> getPlayers() {
        List<Player> copy = new ArrayList<>(players.size());
        Collections.copy(copy, players);
        return copy;
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
     *
     * @param p
     * @param die
     * @param playerId
     * @throws GameMoveException
     * @throws NotValidIdException
     */
    public void placeDie(Point p, Die die, String playerId) throws GameMoveException, NotValidIdException {

        for(Player player : this.players){
            if(player.getId().equals(playerId)){
                player.placeDie(p, die);
                return;
            }
        }

        throw new NotValidIdException("The id: " + playerId + " is not valid.");
    }


    public void placeDie(Point p, Die die, Player player) {
    }

    /**
     *
     * @param playerId
     */
    public int getPlayerTokens(String playerId) {
        // TODO - implement it.polimi.se2018.model.Model.getPlayerTokens
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param die
     */
    public void removeDieFromDraftPool(Die die) {
        // TODO - implement it.polimi.se2018.model.Model.removeDieFromDraftPool
        throw new UnsupportedOperationException();
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



}