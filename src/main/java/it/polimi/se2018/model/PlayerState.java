package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public abstract class PlayerState implements PlayerStateInterface{
    protected boolean diePlaced;
    protected boolean toolActivated;


    public abstract void placeDie(WindowPattern window, Point p, Die die) throws PlacementException;

    public abstract void placeDie(WindowPattern window, int x, int y, Die die) throws NotValidPointException, PlacementException;


    public abstract void useTool(ToolCard card) throws PlacementException;

    public abstract void endTurn();
}
