package it.polimi.se2018.exceptions;

public class NotValidPlayerException extends GameMoveException {
   public NotValidPlayerException(String message){
       super(message);
   }
}
