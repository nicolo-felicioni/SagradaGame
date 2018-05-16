package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.*;

public class FirstTurn extends PlayerState {


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

    }

    @Override
    public void useTool(ToolCard card) throws IllegalMoveTurnException {

    }
}