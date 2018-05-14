package it.polimi.se2018.exceptions;

/**
 * @author PeiQing Gao
 */
public class DiceBagEmptyException extends Exception {
    private final String message;

    public DiceBagEmptyException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
