package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface CommandObservable {

	/**
	 * Add a command observer.
	 *
	 * @param observer the command observer.
	 */
	void addObserver(CommandObserver observer);

	/**
	 * Notify a command.
	 *
	 * @param command the command to be executed.
	 */
	void notify(CommandInterface command);

}
