package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

public class YourTurn extends PlayerState {

    public YourTurn(){
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPlaceDie() {
        return !isDiePlaced();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canUseTool() {
        return !isToolActivated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEndTurn() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void diePlaced() throws IllegalMoveTurnException {
        if(canPlaceDie())
            this.setDiePlaced();
        else throw new IllegalMoveTurnException("There is already a die placed");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useTool(ToolCard card) throws IllegalMoveTurnException {
        if(canUseTool())
            this.setToolActivated(card);
        else throw new IllegalMoveTurnException("there's a toolcard activated already");
    }



}