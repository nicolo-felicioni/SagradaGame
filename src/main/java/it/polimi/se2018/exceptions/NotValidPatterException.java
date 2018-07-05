package it.polimi.se2018.exceptions;

/**
 * @author Nicolò Felicioni
 */

public class NotValidPatterException extends GameMoveException {
    /**
     * @param message
     * @inheritDoc
     */
    public NotValidPatterException(String message) {
        super(message);
    }
}
