package it.polimi.se2018.model;



import it.polimi.se2018.exceptions.GameMoveException;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public class NotYourTurn extends PlayerState {


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
    public void diePlaced() throws IllegalMoveTurnException {
        throw new IllegalMoveTurnException("impossible to place a die, it's not your turn");
    }

    @Override
    public void useTool(ToolCard card) throws IllegalMoveTurnException {
        throw new IllegalMoveTurnException("impossible to use a tool, it's not your turn");
    }
}