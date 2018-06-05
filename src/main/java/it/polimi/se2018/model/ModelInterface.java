package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;

import java.util.*;

public interface ModelInterface {

    /**
     * Adds a player to the current player's set.
     * @param player the player to be added
     * @throws TooManyPlayersException if there are already the maximum number of players.
     */
    public void addPlayer(Player player) throws TooManyPlayersException, NotValidIdException;

    /**
     *Removes a player from the current player's set.
     * @param player player you want to remove
     * @throws NotPresentPlayerException if the player is not present
     */
    public void removePlayer(Player player) throws NotPresentPlayerException ;

    /**
     * Gets a player by his id.
     * The id of a player is of type String and it is unique.
     * @param playerId a string containing the id of the wanted player
     * @return the correspondent player
     * @throws NotValidIdException if there isn't any player with the given id.
     */
    public Player getPlayer(String playerId) throws NotValidIdException ;

    /**
     * gets the dice bag.
     * @return the dice bag.
     */
    public DiceBag getDiceBag();

    /**
     * gets the round track.
     * @return the round track.
     */
    public RoundTrack getRoundTrack();
    /**
     * gets the draft pool.
     * @return the draft pool.
     */
    public DraftPool getDraftPool();


    public ToolCard getToolCard(int n) throws GameException;



    /**
     *
     * @param n
     */
    public PublicObjectiveCard getPublicObjectiveCard(int n) throws GameException;


    /**
     * gets the players in the game.
     * @return a list of players which are in the game.
     */
    public List<Player> getPlayers();

    /**
     * gets all the public objective cards.
     * @return an array with all the public objective cards within.
     */
    public PublicObjectiveCard[] getPublicObjectiveCards();

    /**
     * gets all the tool cards.
     * @return an array with all the tool cards within.
     */
    public ToolCard[] getToolCards();


    /**
     *
     * @param p
     * @param die
     * @param playerId
     * @throws GameMoveException
     * @throws NotValidIdException
     */
    public void placeDie(Point p, Die die, String playerId) throws GameException;


    public void placeDie(Point p, Die die, Player player) throws GameException;

    /**
     *
     * @param playerId
     */
    public int getPlayerTokens(String playerId) throws GameException;

    /**
     *
     * @param die
     */
    public void removeDieFromDraftPool(Die die);


    /**
     * Method that draws a quantity of dice depending on the number of players of the game.
     * The number of dice is the number of players multiplied by 2, all plus one.
     * @return a list of dice
     * @throws DiceBagException if the dice bag hasn't enough dice
     */

    public List<Die> drawDiceFromDiceBag() throws DiceBagException ;


}

