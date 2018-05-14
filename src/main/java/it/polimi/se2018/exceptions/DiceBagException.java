package it.polimi.se2018.exceptions;
/**
 * @author Nicolò Felicioni
 */

public class DiceBagException extends GameMoveException {

    /**
     * @param message
     * @inheritDoc
     */
    public DiceBagException(String message) {
        super(message);
    }
}
