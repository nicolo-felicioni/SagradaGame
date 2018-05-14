package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.FirstTurnPlacementException;
import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

public class FirstTurn extends PlayerState {

    /**
     * checks if a point is at the edge of the window
     * @param p
     * @return true if the point is at the edge, otherwise false
     */


    @Override
    public void placeDie(WindowPattern window, Point p, Die die) throws PlacementException {

        if(!p.isEdgyPoint())
            throw new FirstTurnPlacementException("tried to place a die not at the edge of the window at first turn");


        window.placeDie(die, p);


    }

    @Override
    public void placeDie(WindowPattern window, int x, int y, Die die) throws NotValidPointException, PlacementException {
        Point p = new Point (x, y);

        if(!p.isEdgyPoint())
            throw new FirstTurnPlacementException("tried to place a die not at the edge of the window at first turn");

        if(!window.getSpace(p).isPlaceable(die))
            throw new PlacementException("Color or Value restriction fail");

        window.placeDie(die, p);
    }

    @Override
    public void useTool(ToolCard card) throws GameMoveException {
        //TODO- DA FINIRE
        setToolActivated(true);
    }

    @Override
    public void endTurn() {

    }
}