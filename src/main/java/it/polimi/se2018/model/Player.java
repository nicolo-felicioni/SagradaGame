package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;

import java.util.Arrays;

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


	public Player(String id){
		this.id = id;
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

		//TODO - forse viola il rep
		this.patterns = patterns;
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


	/**
	 * 
	 * @param playerState
	 */
	public void changePlayerStateTo(PlayerState playerState) {
		this.state = playerState;
	}


	public void placeDie(Point p, Die die) throws PlacementException {

		//state.placeDie(chosenPattern, p, die);
	}

	public void placeDie(int x, int y, Die die) throws PlacementException, NotValidPointException {

		Point p = new Point(x, y);
		//TODO- PROVVISORIO
		this.chosenPattern.placeDie(die, p);

	}



	public void useTool(ToolCard card) throws GameMoveException {
		state.useTool(card);
	}

	public void endTurn(){
		//state.endTurn();
	}

	public boolean equalsPlayer(Player player){
		return this.getId().equals(player.getId());
	}

	public String toString(){
		return ("Player id: "+ this.getId());
	}



}