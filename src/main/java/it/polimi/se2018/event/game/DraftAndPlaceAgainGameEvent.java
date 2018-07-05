package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public class DraftAndPlaceAgainGameEvent extends AbstractPlayerDraftedDieGameEvent {

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
	public DraftAndPlaceAgainGameEvent(Die draftedDie, Point point, String id) {
		super(draftedDie, id);
		this.point = point.clonePoint();
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
