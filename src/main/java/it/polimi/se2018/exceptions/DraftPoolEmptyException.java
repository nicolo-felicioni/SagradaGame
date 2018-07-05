package it.polimi.se2018.exceptions;

public class DraftPoolEmptyException extends GameMoveException {
    public DraftPoolEmptyException(String message) {

        super(message);
    }
}
