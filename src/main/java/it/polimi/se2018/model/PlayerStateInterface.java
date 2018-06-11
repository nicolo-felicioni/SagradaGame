package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public interface PlayerStateInterface {

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
	 * @param card the tool card to be activated
	 * @throws IllegalMoveTurnException if the player can't activate the tool card
	 */
	void useTool(ToolCard card) throws IllegalMoveTurnException;


	/**
	 * returns the tool card activated in this turn
	 * @return the tool card activated in this turn
	 */
	ToolCard getActiveToolCard();

}