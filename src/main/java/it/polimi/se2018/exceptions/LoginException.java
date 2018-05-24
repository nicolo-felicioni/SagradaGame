package it.polimi.se2018.exceptions;

/**
 * @author davide yi xian hu
 */
public class LoginException extends NetworkException{

	/**
	 * @inheritDoc
	 */
	public LoginException(String message) {
		super(message);
	}
}
