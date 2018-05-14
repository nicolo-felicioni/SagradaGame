package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public abstract class PlayerState implements PlayerStateInterface{


    private boolean diePlaced;
    private boolean toolActivated;



    public boolean isDiePlaced() {
        return diePlaced;
    }

    protected void setDiePlaced(boolean diePlaced) {
        this.diePlaced = diePlaced;
    }

    public boolean isToolActivated() {
        return toolActivated;
    }

    public void setToolActivated(boolean toolActivated) {
        this.toolActivated = toolActivated;
    }

    public abstract void placeDie(WindowPattern window, Point p, Die die) throws PlacementException;

    public abstract void placeDie(WindowPattern window, int x, int y, Die die) throws NotValidPointException, PlacementException;


    public abstract void useTool(ToolCard card) throws GameMoveException, GameMoveException;

    public abstract void endTurn();
}
