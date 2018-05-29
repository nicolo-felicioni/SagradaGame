package it.polimi.se2018.exceptions;

public class TooManyPlayersException extends Exception {
    public TooManyPlayersException(String message){
        super(message);
    }
}
