package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.IllegalMoveTurnException;

public class ChooseWindowPatternState extends PlayerState {


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
    public void useTool(ToolCard card) throws IllegalMoveTurnException {
        throw new IllegalMoveTurnException("Haven't chosen a window pattern yet");
    }
}
