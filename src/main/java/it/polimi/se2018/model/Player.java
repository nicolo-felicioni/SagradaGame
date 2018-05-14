package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.NotValidPatternVectorException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public class Player {

	private String id;
	private int favorTokens;
	private PrivateObjectiveCard privateObjectiveCard;
	private WindowPattern[] patterns; //the initial four patterns
	private WindowPattern chosenPattern; //the chosen one
	private PlayerState state;
	private ToolCard activeToolCard;


	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id a string
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @param patterns a vector formed of four window pattern
	 */
	public void setPatterns(WindowPattern[] patterns) throws NotValidPatternVectorException {

		if(patterns.length != 4)
			throw new NotValidPatternVectorException("vector length:" + patterns.length);

		//TODO - forse viola il rep
		this.patterns = patterns;
	}

	public WindowPattern[] getPatterns() {
		return this.patterns;
	}

	public int getTokens() {
		return favorTokens;
	}

	public WindowPattern getPattern() {
		return chosenPattern;
	}

	public PrivateObjectiveCard getPrivateObjective() {
		return privateObjectiveCard;
	}

	public void setPrivateObjective(PrivateObjectiveCard card) {
		this.privateObjectiveCard = card;
	}

	public ToolCard getActiveToolCard() {
		return activeToolCard;
	}

	public void setActiveToolCard(ToolCard activeToolCard) {
		this.activeToolCard = activeToolCard;
	}



	/**
	 * 
	 * @param pattern
	 */
	public void choosePattern(WindowPattern pattern) {
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
		state.placeDie(chosenPattern, p, die);
	}

	public void placeDie(int x, int y, Die die) throws PlacementException, NotValidPointException {

		Point p = new Point(x, y);
		state.placeDie(chosenPattern, p, die);
	}

	public void useTool(ToolCard card) throws GameMoveException {
		state.useTool(card);
	}

	public void endTurn(){
		state.endTurn();
	}

}