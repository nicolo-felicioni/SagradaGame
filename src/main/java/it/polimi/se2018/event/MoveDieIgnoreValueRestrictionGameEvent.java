package it.polimi.se2018.event;

import it.polimi.se2018.model.Point;
import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public class MoveDieIgnoreValueRestrictionGameEvent extends AbstractMoveDieGameEvent {

	/**
	 * Constructor.
	 * @param initialPosition the initial position;
	 * @param finalPosition the final position;
	 * @param playerId the player identifier.
	 */
	public MoveDieIgnoreValueRestrictionGameEvent(Point initialPosition, Point finalPosition, String playerId) {
		super(initialPosition, finalPosition, playerId);
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
