package it.polimi.se2018.exceptions;
/**
 * @author Nicolò Felicioni
 */

public class NotValidNumberOfDiceException extends DiceBagException {
    /**
     * @param message
     * @inheritDoc
     */
    public NotValidNumberOfDiceException(String message) {
        super(message);
    }
}
