package it.polimi.se2018.event;

import it.polimi.se2018.model.Point;

/**
 * @author davide yi xian hu
 */
public abstract class AbstractMoveDieEvent extends AbstractPlayerEvent{

	/**
	 * Initial position.
	 */
	private Point initialPosition;

	/**
	 * Final position.
	 */
	private Point finalPosition;

	/**
	 * Constructor.
	 * @param initialPosition the initial position;
	 * @param finalPosition the final position;
	 * @param playerId the player identifier.
	 */
	public AbstractMoveDieEvent(Point initialPosition, Point finalPosition, String playerId) {
		super(playerId);
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
	}

	/**
	 * Getter of the initial position.
	 * @return the initial position.
	 */
	public Point getInitialPosition() {
		return initialPosition;
	}

	/**
	 * Getter of the final position.
	 * @return the final position.
	 */
	public Point getFinalPosition() {
		return finalPosition;
	}
}
