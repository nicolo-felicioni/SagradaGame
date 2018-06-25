package it.polimi.se2018.model;



import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public class NotYourTurnState extends PlayerState {

    /**
     * Constructor.
     */
    public NotYourTurnState() {
        super();
    }

    /**
     * Copy constructor.
     * @param state the state. It has to be a Not Your Turn state.
     */
    public NotYourTurnState(NotYourTurnState state) {
        super(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPlaceDie() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canUseTool() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEndTurn() {
        return false;
    }

    @Override
    public boolean hasChosenWindowPattern() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void diePlaced() throws IllegalMoveTurnException {
        throw new IllegalMoveTurnException("impossible to place a die, it's not your turn");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useTool() throws IllegalMoveTurnException {
        throw new IllegalMoveTurnException("impossible to use a tool, it's not your turn");
    }

    /**
     * Clone the player state. Return a copy.
     *
     * @return a clone of this state.
     */
    @Override
    public PlayerState cloneState() {
        return new NotYourTurnState(this);
    }
}