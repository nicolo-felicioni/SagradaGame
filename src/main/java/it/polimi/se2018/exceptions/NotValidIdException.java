package it.polimi.se2018.exceptions;

public class NotValidIdException extends GameMoveException {
    public NotValidIdException(String message){
        super(message);
    }
}
