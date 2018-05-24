package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface CommandObserver {

	/**
	 * Update the command to the observer.
	 *
	 * @param command the command that have to be notified to the observer.
	 */
	void update(CommandInterface command);

}
