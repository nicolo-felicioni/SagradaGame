package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public interface PlayerStateInterface {

	boolean canPlaceDie();

	boolean canUseTool();

	boolean canEndTurn();

	void diePlaced() throws IllegalMoveTurnException;

	void useTool(ToolCard card) throws IllegalMoveTurnException;

	ToolCard getActiveToolCard();

}