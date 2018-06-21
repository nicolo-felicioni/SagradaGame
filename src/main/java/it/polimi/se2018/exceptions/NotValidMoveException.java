package it.polimi.se2018.exceptions;

public class NotValidMoveException extends GameException{
    public NotValidMoveException() { super(); }
    public NotValidMoveException(String message) { super(message); }
}
