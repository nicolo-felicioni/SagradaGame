package it.polimi.se2018.controller;

import it.polimi.se2018.model.Model;

/**
 * @author davide yi xian hu
 */
public class Controller implements CommandObserver{

	private Model model;

	/**
	 * Notify the command to the observer.
	 *
	 * @param command the command that have to be notified to the observer.
	 */
	@Override
	public void handle(CommandInterface command) {
		command.executeCommand(model);
	}

}
