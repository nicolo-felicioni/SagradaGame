package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface CommandInterface {

	/**
	 * Execute this command.
	 * @param controller the object that have to be modified by this command.
	 */
	void executeCommand (Controller controller);
}
