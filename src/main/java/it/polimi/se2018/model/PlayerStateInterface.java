package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public interface PlayerStateInterface {



	void placeDie(WindowPattern window, Point p, Die die) throws PlacementException;

	void placeDie(WindowPattern window, int x, int y, Die die) throws NotValidPointException, PlacementException;

	void useTool(ToolCard card) throws GameMoveException;

	void endTurn();

}