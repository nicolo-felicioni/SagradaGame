package it.polimi.se2018.exceptions;

/**
 * @author davide yi xian hu
 */
public class GameException extends Exception{

	/**
	 * A textual representation of the exception.
	 */
	private String message;

	public GameException(){
		super();
	}

	/**
	 * Constructor with a message.
	 * @param message the message of the exception
	 */
	public GameException(String message) {
		this.message = message;
	}

	/**
	 * Getter of the message of the exception.
	 * @return
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
