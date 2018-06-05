package it.polimi.se2018.controller.command;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;

/**
 * @author davide yi xian hu
 */
public class StartGameCommand implements CommandInterface {

	/**
	 * Execute this command.
	 *
	 * @param controller the object that have to be modified by this command.
	 * @param model the object that have to be modified by this command.
	 */
	@Override
	public void executeCommand(Controller controller, Model model) {
		//TODO il controller ha metodi pubblici?
		controller.startGame();
	}
}
