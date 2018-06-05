package it.polimi.se2018.event;

import it.polimi.se2018.model.Point;

/**
 * @author davide yi xian hu
 */
public class MoveDieIgnoreValueRestrictionEvent extends AbstractMoveDieEvent{

	/**
	 * Constructor.
	 * @param initialPosition the initial position;
	 * @param finalPosition the final position;
	 * @param playerId the player identifier.
	 */
	public MoveDieIgnoreValueRestrictionEvent(Point initialPosition, Point finalPosition, String playerId) {
		super(initialPosition, finalPosition, playerId);
	}

}
