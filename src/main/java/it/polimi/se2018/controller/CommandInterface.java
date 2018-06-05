package it.polimi.se2018.controller;

import it.polimi.se2018.model.Model;

/**
 * @author davide yi xian hu
 */
public interface CommandInterface { // = ModelUpdater

	/**
	 * Execute this command.
	 * @param controller the object that have to be modified by this command.
	 * @param model the object that have to be modified by this command.
	 */
	void executeCommand (Controller controller, Model model);
}
