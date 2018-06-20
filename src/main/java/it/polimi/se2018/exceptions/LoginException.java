package it.polimi.se2018.exceptions;

/**
 * @author davide yi xian hu
 */
public class LoginException extends NetworkException{

	/**
	 * Login error message.
	 */
	public static final String DEFAULT_LOGIN_ERROR_MESSAGE = "Login error";

	/**
	 * @inheritDoc
	 */
	public LoginException(String message) {
		super(message);
	}
}
