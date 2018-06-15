package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

import java.io.Serializable;

/**
 * @author Nicolò Felicioni
 */

public interface PlayerStateInterface extends Serializable {

	/**
	 * returns true if the player has already placed a die in this turn
	 * @return true if the player has already placed a die in this turn
	 */
	boolean canPlaceDie();

	/**
	 * returns true if the player has already activated a tool card in this turn
	 * @return true if the player has already activated a tool card in this turn
	 */
	boolean canUseTool();

	/**
	 * returns true if the player can end his turn
	 * @return true if you the player can end his turn
	 */
	boolean canEndTurn();

	/**
	 * returns true if the player has already chosen his window pattern.
	 * @return true if the player has already chosen his window pattern.
	 */
	boolean hasChosenWindowPattern();

	/**
	 * method to be called after a placement of a die
	 */
	void diePlaced() throws IllegalMoveTurnException;

	/**
	 * method that activates the tool card
	 * @throws IllegalMoveTurnException if the player can't activate the tool card
	 */
	void useTool() throws IllegalMoveTurnException;

	/**
	 * Clone the player state. Return a copy.
	 * @return a clone of this state.
	 */
	PlayerState cloneState();

}