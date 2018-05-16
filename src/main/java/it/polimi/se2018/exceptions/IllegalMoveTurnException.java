package it.polimi.se2018.exceptions;

public class IllegalMoveTurnException extends GameMoveException {

    /**
     * @param message
     * @inheritDoc
     */
    public IllegalMoveTurnException(String message) {
        super(message);
    }
}
