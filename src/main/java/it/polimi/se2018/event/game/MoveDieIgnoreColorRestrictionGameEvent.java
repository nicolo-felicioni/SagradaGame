package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Point;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public class MoveDieIgnoreColorRestrictionGameEvent extends AbstractMoveDieGameEvent {

	/**
	 * Constructor.
	 * @param initialPosition the initial position;
	 * @param finalPosition the final position;
	 * @param playerId the player identifier.
	 */
	public MoveDieIgnoreColorRestrictionGameEvent(Point initialPosition, Point finalPosition, String playerId) {
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
