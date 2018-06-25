package it.polimi.se2018.model;


/**
 * @author Nicolò Felicioni
 */

public abstract class PlayerState implements PlayerStateInterface{

    private boolean diePlaced ;
    private boolean toolActivated ;



    protected PlayerState(){
        this.diePlaced=false;
        this.toolActivated=false;
    }

    /**
     * Copy constructor.
     * @param state the state to be copied.
     */
    protected PlayerState(PlayerState state) {
        this.diePlaced = state.isDiePlaced();
        this.toolActivated = state.isToolActivated();
    }

    /**
     * returns true if you have already placed a die in this turn
     * @return true if you have already placed a die in this turn
     */
    public boolean isDiePlaced(){
        return diePlaced;
    }

    /**
     * returns true if you have already activated a tool card in this turn
     * @return true if you have already activated a tool card in this turn
     */
    boolean isToolActivated(){
        return toolActivated;
    }



    void setDiePlaced(){
        this.diePlaced = true;
    }

    void setToolActivated(){
        this.toolActivated = true;
    }

}
