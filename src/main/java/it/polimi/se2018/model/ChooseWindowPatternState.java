package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.IllegalMoveTurnException;

public class ChooseWindowPatternState extends PlayerState {

    /**
     * Constructor.
     */
    public ChooseWindowPatternState() {
        super();
    }

    /**
     * Copy constructor.
     * @param state the state. It has to be a Choose Window Pattern state.
     */
    public ChooseWindowPatternState(ChooseWindowPatternState state) {
        super(state);
    }

    @Override
    public boolean canPlaceDie() {
        return false;
    }

    @Override
    public boolean canUseTool() {
        return false;
    }

    @Override
    public boolean canEndTurn() {
        return false;
    }

    @Override
    public boolean hasChosenWindowPattern() {
        return false;
    }

    @Override
    public void diePlaced() throws IllegalMoveTurnException {
        throw new IllegalMoveTurnException("Haven't chosen a window pattern yet");
    }

    @Override
    public void useTool() throws IllegalMoveTurnException {
        throw new IllegalMoveTurnException("Haven't chosen a window pattern yet");
    }

    /**
     * Clone the player state. Return a copy.
     *
     * @return a clone of this state.
     */
    @Override
    public PlayerState cloneState() {
        return new ChooseWindowPatternState(this);
    }


}
