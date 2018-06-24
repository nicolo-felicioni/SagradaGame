package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public class DraftAndPlaceGameEvent extends AbstractPlayerGameEvent {

	/**
	 * The die of the draft pool.
	 */
	private Die draftedDie;

	/**
	 * The position of the space in a window pattern;
	 */
	private Point point;

	/**
	 * Constructor.
	 * @param draftedDie the die of the draft pool.
	 * @param point the position of the space in a window pattern.
	 * @param id the identifier of the player.
	 */
	public DraftAndPlaceGameEvent(Die draftedDie, Point point, String id) {
		super(id);
		this.draftedDie = draftedDie.cloneDie();
		this.point = point.clonePoint();
	}

	/**
	 * Getter of the drafted die from the draft pool.
	 * @return the drafted die from the draft pool.
	 */
	public Die getDraftedDie() {
		return draftedDie.cloneDie();
	}

	/**
	 * Getter of the position of the space in a window pattern.
	 * @return the position of the space in a window pattern.
	 */
	public Point getPoint() {
		return point.clonePoint();
	}

	/**
	 * Accept an observer. Visitor pattern.
	 *
	 * @param observer the observer to be called.
	 */
	@Override
	public void accept(GameEventObserver observer) {
		observer.handle(this);
	}
}
