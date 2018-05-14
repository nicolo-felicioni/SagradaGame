package it.polimi.se2018.exceptions;

/**
 * @author PeiQing Gao
 */
public class NotEnoughDiceException extends DiceBagException {
    /**
     * @param message
     * @inheritDoc
     */
    public NotEnoughDiceException(String message) {
        super(message);
    }
}
