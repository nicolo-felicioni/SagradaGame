package it.polimi.se2018.controller.command;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.exceptions.ToolCardStateException;
import it.polimi.se2018.model.Model;

/**
 * @author davide yi xian hu
 */
public class UseToolCardCommand implements CommandInterface {

	/**
	 * The position of the tool card.
	 */
	private int positionOfToolCard;

	/**
	 * Constructor.
	 * @param position The position of the tool card.
	 */
	public UseToolCardCommand(int position) {
		this.positionOfToolCard = position;
	}

	/**
	 * Execute this command.
	 *
	 * @param controller the object that have to be modified by this command.
	 * @param model the object that have to be modified by this command.
	 */
	@Override
	public void executeCommand(Controller controller, Model model) {
		try {
			model.activateToolCard(positionOfToolCard);
			//TODO use the player favor tokens.
		} catch (ToolCardStateException e) {
			e.printStackTrace();
		}

	}
}
