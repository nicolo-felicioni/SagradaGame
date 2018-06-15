package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.*;

public class FirstTurnState extends PlayerState {

    /**
     * Constructor.
     */
    public FirstTurnState() {
        super();
    }

    /**
     * Copy constructor.
     * @param state the state. It has to be a Choose Window Pattern state.
     */
    public FirstTurnState(FirstTurnState state) {
        super(state);
    }

    @Override
    public boolean canPlaceDie() {
        return !isDiePlaced();
    }

    @Override
    public boolean canUseTool() {
        return !isToolActivated();
    }

    @Override
    public boolean canEndTurn() {
        return isDiePlaced(); //in the very first turn the player can end his turn only if he had already placed a die
    }

    @Override
    public boolean hasChosenWindowPattern() {
        return true;
    }

    @Override
    public void diePlaced() throws IllegalMoveTurnException {
        if(canPlaceDie())
            this.setDiePlaced();
        else throw new IllegalMoveTurnException("You have already placed a die in this turn!");
    }

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
        return new FirstTurnState(this);
    }
}