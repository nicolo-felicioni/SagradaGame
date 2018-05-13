package it.polimi.se2018.model;



import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public class NotYourTurn extends PlayerState {

    @Override
    public void placeDie(WindowPattern window, Point p, Die die) throws PlacementException {
        throw new PlacementException("The player tried to place a die when it wasn't his turn");
    }

    @Override
    public void placeDie(WindowPattern window, int x, int y, Die die) throws NotValidPointException, PlacementException {
        throw new PlacementException("The player tried to place a die when it wasn't his turn");
    }

    @Override
    public void useTool(ToolCard card) throws PlacementException {
        throw new PlacementException("The player tried to usa a tool when it wasn't his turn");
    }

    @Override
    public void endTurn() {

    }
}