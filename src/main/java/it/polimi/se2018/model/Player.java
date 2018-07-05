package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;

import java.util.Arrays;

/**
 * @author Nicolò Felicioni
 */

public class Player {

	/**
	 * the player id
	 */
	private final String id;

	/**
	 * the favor tokens of the player
	 */
	private int favorTokens;

	/**
	 * the private objective card of the player
	 */
	private PrivateObjectiveCard privateObjectiveCard;

	/**
	 * the initial window patterns
	 */
	private WindowPattern[] patterns;

	/**
	 * the chosen one patter
	 */
	private WindowPattern chosenPattern;

	/**
	 * the state of the player.
	 *
	 */
	private PlayerState state;

	/**
	 * If the player is connected.
	 */
	private boolean connected;

	/**
	 * constant field. the number of the initial window patterns.
	 */
	public static final int N_WINDOW_PATTERNS = 4;


	/**
	 * constructor.
	 * @param id the id of the player.
	 */
	public Player(String id) {
		this.id = id;
		this.state = null;
		this.privateObjectiveCard = null;
		this.patterns = null;
		this.connected = true;
	}


	/**
	 * copy constructor.
	 *
	 * @param copy the player to be copied
	 */
	public Player(Player copy) {
		this.id = copy.getId();
		if (copy.patterns != null)
			this.patterns = copy.getPatterns();
		if (copy.chosenPattern != null)
			this.chosenPattern = copy.chosenPattern.cloneWindowPattern();
		this.privateObjectiveCard = copy.privateObjectiveCard;
		this.state = copy.state;
		this.favorTokens = copy.getTokens();
		this.connected = copy.isConnected();

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
	 *
	 * @param patterns a vector formed of four window pattern
	 */
	public void setPatterns(WindowPattern[] patterns) throws NotValidPatternVectorException {
		if (patterns.length != N_WINDOW_PATTERNS)
			throw new NotValidPatternVectorException("vector length:" + patterns.length);
		this.patterns = new WindowPattern[N_WINDOW_PATTERNS];
		for (int i = 0; i < N_WINDOW_PATTERNS; i++) {
			this.patterns[i] = patterns[i].cloneWindowPattern();
		}
	}

	/**
	 * this method returns an array with the 4 pattern this player can choose before starting the game.
	 *
	 * @return an array with the 4 pattern this player can choose before starting the game.
	 */
	public WindowPattern[] getPatterns() {
		WindowPattern[] windows = new WindowPattern[N_WINDOW_PATTERNS];
		for (int i = 0; i < N_WINDOW_PATTERNS; i++) {
			windows[i] = this.patterns[i].cloneWindowPattern();
		}
		return windows;
	}

	/**
	 * gets the number of favor tokens this player has.
	 *
	 * @return the number of favor tokens this player has.
	 */
	public int getTokens() {
		return favorTokens;
	}

	/**
	 * gets player's pattern.
	 *
	 * @return player's pattern.
	 */
	public WindowPattern getPattern() {
		if (chosenPattern != null) {
			return chosenPattern.cloneWindowPattern();
		} else {
			return null;
		}
	}


	/**
	 * gets player's private objective card.
	 *
	 * @return player's private objective card.
	 */
	public PrivateObjectiveCard getPrivateObjective() {
		//immutable
		return privateObjectiveCard;
	}

	/**
	 * sets player's private objective card.
	 *
	 * @param card it's the player's private objective card.
	 */
	public void setPrivateObjective(PrivateObjectiveCard card) {
		this.privateObjectiveCard = card;
	}


	/**
	 * This method choose which of the four possible window patterns the player will use during the game.
	 *
	 * @param pattern the chosen pattern
	 * @throws NotValidPatterException if the pattern is not present in the four possible patterns.
	 */
	public void choosePattern(WindowPattern pattern) throws NotValidPatterException {
		if (Arrays.stream(this.patterns).noneMatch(p -> p.equalsWindowPattern(pattern))) {
			throw new NotValidPatterException("the pattern " + pattern + "is not present in the four possible patterns");
		}
		this.chosenPattern = pattern;
		this.favorTokens = pattern.getDifficulty();
	}

	/**
	 * This method choose which of the four possible window patterns the player will use during the game.
	 *
	 * @param pattern the chosen pattern
	 * @throws NotValidPatterException if the pattern is not present in the four possible patterns.
	 */
	public void setPattern(WindowPattern pattern) throws NotValidPatterException {
		if (this.chosenPattern.getName().equals(pattern.getName())) {
			this.chosenPattern = pattern;
		} else
			throw new NotValidPatterException("The pattern is not valid, the new pattern must have the same name as the old.");
	}

	/**
	 * Setter of the chosen window pattern.
	 *
	 * @param pattern the chosen pattern
	 */
	public void setChosenPattern(WindowPattern pattern) {
		this.chosenPattern = pattern.cloneWindowPattern();
		this.favorTokens = pattern.getDifficulty();
	}


	/**
	 * getter of the player's state.
	 * @return the player's state.
	 */
	public PlayerState getState() {
		if (state != null) {
			return this.state.cloneState();
		} else {
			return null;
		}
	}


	/**
	 * setter of the player's state
	 * @param playerState the player's state to set.
	 */
	public void changePlayerStateTo(PlayerState playerState) {
		this.state = playerState.cloneState();
	}


	/**
	 * this method places a die in a point of the chosen window pattern of the player.
	 *
	 * it will check all constrictions following the game's rules.
	 *
	 *
	 * @param p the point where the die should be placed
	 * @param die the die that should be placed
	 * @throws PlacementException if the die can't placed in the point p
	 * @throws IllegalMoveTurnException if it's not this player's turn.
	 */
	public void placeDie(Point p, Die die) throws PlacementException, IllegalMoveTurnException {

		if (this.state.canPlaceDie()) {
			this.chosenPattern.placeDie(die, p);
		}
		this.state.diePlaced();
	}

	/**
	 * this method places a die in a point of the chosen window pattern of the player.
	 *
	 * it will check all constrictions following the game's rules.
	 *
	 *
	 * @param x the first coordinate of the point where the die should be placed
	 * @param y the second coordinate of the point where the die should be placed
	 * @param die the die that should be placed
	 * @throws GameMoveException if the die can't placed in the point p
	 * @throws NotValidDieException if the coordinates are not valid.
	 */
	public void placeDie(int x, int y, Die die) throws GameMoveException, NotValidPointException {

		Point p = new Point(x, y);

		if (this.state.canPlaceDie()) {
			this.chosenPattern.placeDie(die, p);
		}

		this.state.diePlaced(); //if it can't be placed, throws exception

	}


	/**
	 * Return true if a die can be placed in a space of the chosen window pattern respecting all restrictions.
	 *
	 * @param p   the position of the space in the window pattern.
	 * @param die the die.
	 * @return true if a die can be placed in a space of the chosen window pattern respecting all restrictions.
	 */
	public boolean isPlaceable(Point p, Die die) {
		return this.chosenPattern.isPlaceable(die, p);
	}

	/**
	 * Spend player's favor tokens.
	 *
	 * @param amount the amount that have to be spent.
	 * @throws NotEnoughTokenException if player has not enough favor tokens.
	 */
	public void spendToken(int amount) throws NotEnoughTokenException {
		if (this.favorTokens >= amount) {
			this.favorTokens = this.favorTokens - amount;
		} else {
			throw new NotEnoughTokenException("your favor tokens: " + this.getTokens() +
					"\nFavor tokens needed: " + amount);
		}
	}

	/**
	 * the method that has to be called to use a tool card.
	 *
	 * @throws GameMoveException if the player can't use a tool card.
	 */
	public void useTool() throws GameMoveException {
		this.state.useTool();
	}

	/**
	 * Indicates whether some other player is "equal to" this one.
	 * @param player the player with which to compare.
	 * @return true if the players are equal
	 */
	public boolean equalsPlayer(Player player) {
		return this.getId().equals(player.getId());
	}


	/**
	 * Returns a string representation of the player.
	 * @return a string representation of the player.
	 */
	public String toString() {
		return ("Player id: " + this.getId());
	}

	/**
	 * Return true if the player has chosen a window pattern.
	 *
	 * @return true if the player has chosen a window pattern.
	 */
	public boolean hasChosenWindowPattern() {
		return chosenPattern != null;
	}

	/**
	 * Return a clone of this player.
	 *
	 * @return a clone of this player.
	 */
	public Player clonePlayer() {
		return new Player(this);
	}

	/**
	 * Return true if the player is connected.
	 *
	 * @return true if the player is connected.
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Set the connection state of the player, true if it is connected, false otherwise.
	 *
	 * @param connected true if it is connected, false otherwise.
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * Set the number of favor tokens of the player.
	 *
	 * @param favorTokens the number of favor tokens of the player.
	 */
	public void setTokens(int favorTokens) {
		this.favorTokens = favorTokens;
	}

}