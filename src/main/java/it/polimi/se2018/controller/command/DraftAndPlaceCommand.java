package it.polimi.se2018.controller.command;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.exceptions.GameException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Point;

/**
 * @author davide yi xian hu
 */
public class DraftAndPlaceCommand implements CommandInterface {

	/**
	 * The die of the draft pool.
	 */
	private Die draftedDie;

	/**
	 * The position of the space in a window pattern;
	 */
	private Point point;

	/**
	 * The identifier of the player.
	 */
	private String playerId;

	/**
	 * Constructor.
	 * @param draftedDie the die of the draft pool.
	 * @param point the position of the space in a window pattern;
	 * @param id the identifier of the player.
	 */
	public DraftAndPlaceCommand(Die draftedDie, Point point, String id) {
		this.draftedDie = draftedDie;
		this.point = point;
		this.playerId = id;
	}

	/**
	 * Execute this command.
	 *
	 * @param controller the object that have to be modified by this command.
	 * @param model the object that have to be modified by this command.
	 */
	@Override
	public void executeCommand(Controller controller,  Model model) {
		try {
			model.placeDie(point, draftedDie, playerId);
			model.removeDieFromDraftPool(draftedDie);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
