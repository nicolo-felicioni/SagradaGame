package it.polimi.se2018.exceptions;

/**
 * @author PeiQing Gao
 */
public class NotEnoughDiceException extends Exception {
    private final String message;

    public NotEnoughDiceException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
