package it.polimi.se2018.exceptions;

/**
 * @author davide yi xian hu
 */
public class GameMoveException extends Exception{

	/**
	 * A textual representation of the exception.
	 */
	private final String message;

	/**
	 * Constructor with a message.
	 * @param message the message of the exception
	 */
	public GameMoveException(String message) {
		this.message = message;
	}

	/**
	 * Getter of the message of the exception.
	 * @return the message of the exception.
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
