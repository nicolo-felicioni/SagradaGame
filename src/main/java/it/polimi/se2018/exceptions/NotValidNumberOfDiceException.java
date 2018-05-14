package it.polimi.se2018.exceptions;
/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.model.DiceBag;

public class NotValidNumberOfDiceException extends DiceBagException {
    /**
     * @param message
     * @inheritDoc
     */
    public NotValidNumberOfDiceException(String message) {
        super(message);
    }
}
