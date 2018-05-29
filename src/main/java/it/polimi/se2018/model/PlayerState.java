package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public abstract class PlayerState implements PlayerStateInterface{

    private boolean diePlaced ;
    private boolean toolActivated ;
    private ToolCard activeTool ;




    protected PlayerState(){
        this.diePlaced=false;
        this.toolActivated=false;
        ToolCard activeTool = null;
    }


    /**
     * returns true if you have already placed a die in this turn
     * @return true if you have already placed a die in this turn
     */
    protected boolean isDiePlaced(){
        return diePlaced;
    }

    /**
     * returns true if you have already activated a tool card in this turn
     * @return true if you have already activated a tool card in this turn
     */
    protected boolean isToolActivated(){
        return toolActivated;
    }

    protected void setDiePlaced(){
        this.diePlaced = true;
    }

    protected void setToolActivated(ToolCard tool){
        this.activeTool=tool;
        this.toolActivated = true;
    }

    public ToolCard getActiveToolCard(){
        return this.activeTool;
    }

}
