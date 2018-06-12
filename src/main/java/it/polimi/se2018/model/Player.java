package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nicolò Felicioni
 */

public class Player {

	private final String id;
	private int favorTokens;
	private PrivateObjectiveCard privateObjectiveCard;
	private WindowPattern[] patterns; //the initial four patterns
	private WindowPattern chosenPattern; //the chosen one
	private PlayerState state;
	private ToolCard activeToolCard;

	public static final int N_WINDOW_PATTERNS = 4;


	public Player(String id){
		this.id = id;
		this.activeToolCard=null;
		this.state=null;
		this.privateObjectiveCard=null;
		this.patterns = null;
	}


	/**
	 * copy constructor.
	 * @param copy
	 */
	//TODO - da rivedere

	public Player(Player copy){
		this.id = copy.getId();
		if(copy.patterns != null)
			this.patterns = Arrays.copyOf(copy.patterns, copy.patterns.length);
		if(copy.chosenPattern != null)
			this.chosenPattern=copy.chosenPattern.cloneWindowPattern();
		this.privateObjectiveCard=copy.privateObjectiveCard;
		this.state=copy.state;
		this.activeToolCard=copy.activeToolCard;

	}

	/**
	 * this method returns the id of the player.
	 *
	 * @return his id.
	 */
	public String getId() {
		return this.id;
	}


	/**
	 * This is a setter for the 4 patterns a player can choose before starting the game.
	 * @param patterns a vector formed of four window pattern
	 */
	public void setPatterns(WindowPattern[] patterns) throws NotValidPatternVectorException {

		if(patterns.length != 4)
			throw new NotValidPatternVectorException("vector length:" + patterns.length);

		this.patterns = Arrays.copyOf(patterns, patterns.length);
	}

	/**
	 * this method returns an array with the 4 pattern this player can choose before starting the game.
	 * @return an array with the 4 pattern this player can choose before starting the game.
	 */
	public WindowPattern[] getPatterns() {
		return Arrays.copyOf(this.patterns, this.patterns.length);
	}

	/**
	 * gets the number of favor tokens this player has.
	 * @return the number of favor tokens this player has.
	 */
	public int getTokens() {
		return favorTokens;
	}

	/**
	 * gets player's pattern.
	 * @return player's pattern.
	 */
	public WindowPattern getPattern() {
		return chosenPattern.cloneWindowPattern();
	}


	/**
	 * gets player's private objective card.
	 * @return player's private objective card.
	 */
	public PrivateObjectiveCard getPrivateObjective() {
		//immutable
		return privateObjectiveCard;
	}

	/**
	 * sets player's private objective card.
	 * @param card it's the player's private objective card.
	 */
	public void setPrivateObjective(PrivateObjectiveCard card) {
		this.privateObjectiveCard = card;
	}


	//TODO - DUBBIA GESTIONE DELLE TOOL CARD
	public ToolCard getActiveToolCard() {
		return activeToolCard;
	}

	public void setActiveToolCard(ToolCard activeToolCard) {
		this.activeToolCard = activeToolCard;
	}



	/**
	 * This method choose which of the four possible window patterns the player will use during the game.
	 * @param pattern the chosen pattern
	 * @throws NotValidPatterException if the pattern is not present in the four possible patterns.
	 */
	public void choosePattern(WindowPattern pattern) throws NotValidPatterException {
		if(Arrays.stream(this.patterns).noneMatch(p -> p.equalsWindowPattern(pattern))){
			throw new NotValidPatterException("the pattern "+ pattern + "is not present in the four possible patterns");
		}
		this.chosenPattern = pattern;
		this.favorTokens = pattern.getDifficulty();

	}


	public PlayerState getState(){
		//TODO - forse c'è bisogno del clone dello state
		return this.state;
	}


	/**
	 * 
	 * @param playerState
	 */
	public void changePlayerStateTo(PlayerState playerState) {
		this.state = playerState;
	}


	public void placeDie(Point p, Die die) throws PlacementException, IllegalMoveTurnException {

	    //TODO - Non so

		if(this.state.canPlaceDie()){
            this.chosenPattern.placeDie(die, p);
        }

        this.state.diePlaced();

	}

	public void placeDie(int x, int y, Die die) throws GameMoveException, NotValidPointException {

		Point p = new Point(x, y);

        //TODO - Non so

        if(this.state.canPlaceDie()){
            this.chosenPattern.placeDie(die, p);
        }

        this.state.diePlaced(); //if it can't be placed, throws exception

	}

	/**
	 * Return if a die can be placed in a space of the chosen window pattern respecting all restrictions.
	 * @param p the position of the space in the window pattern.
	 * @param die the die.
	 * @return true if a die can be placed in a space of the chosen window pattern respecting all restrictions.
	 */
	public boolean isPlaceable(Point p, Die die){
		return this.chosenPattern.isPlaceable(die, p);
	}

	/**
	 * Spend player's favor tokens.
	 * @param amount the amount that have to be spent.
	 * @throws NotEnoughTokenException if player has not enough favor tokens.
	 */
	public void spendToken(int amount) throws NotEnoughTokenException{
		if(this.favorTokens >= amount) {
			this.favorTokens = this.favorTokens - amount;
		}else{
			throw new NotEnoughTokenException("your favor tokens: "+ this.getTokens() +
					"\nFavor tokens needed: " + amount);
		}
	}

	public void useTool(ToolCard card) throws GameMoveException {
		if(this.state.canUseTool()){
		    this.useTool(card);
		    this.setActiveToolCard(card);
        }

        this.state.useTool(card); //if it can't be used, throws exception

	}

	public void endTurn() throws GameMoveException{
		if(this.state.canEndTurn())
		    this.endTurn();
		else throw new IllegalMoveTurnException("You can't end turn");
	}

	public boolean equalsPlayer(Player player){
		return this.getId().equals(player.getId());
	}

	public String toString(){
		return ("Player id: "+ this.getId());
	}

	/**
	 * Change the player state to YourTurn.
	 */
	public void startTurn() {
		this.changePlayerStateTo(new YourTurnState());
	}


}