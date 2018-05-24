package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public class Controller implements CommandObserver{

	/**
	 * Notify the command to the observer.
	 *
	 * @param command the command that have to be notified to the observer.
	 */
	@Override
	public void update(CommandInterface command) {
		command.executeCommand(this);
	}

}
