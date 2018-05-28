package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface CommandObserver {

	/**
	 * Handle the command.
	 *
	 * @param command the command that have to be notified to the observer.
	 */
	void handle(CommandInterface command);

}
