package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

public class YourTurnState extends PlayerState {

    /**
     * Constructor.
     */
    public YourTurnState() {
        super();
    }

    /**
     * Copy constructor.
     * @param state the state. It has to be a Your Turn state.
     */
    public YourTurnState(YourTurnState state) {
        super(state);
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
    public boolean hasChosenWindowPattern() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void diePlaced() throws IllegalMoveTurnException {
        if(canPlaceDie())
            this.setDiePlaced();
        else throw new IllegalMoveTurnException("You have already placed a die in this turn!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useTool() throws IllegalMoveTurnException {
        if(canUseTool())
            this.setToolActivated();
        else throw new IllegalMoveTurnException("there's a toolcard activated already");
    }

    /**
     * Clone the player state. Return a copy.
     *
     * @return a clone of this state.
     */
    @Override
    public PlayerState cloneState() {
        return  new YourTurnState(this);
    }


}