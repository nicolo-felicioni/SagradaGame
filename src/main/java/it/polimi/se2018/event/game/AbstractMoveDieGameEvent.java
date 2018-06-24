package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Point;

/**
 * @author Davide Yi Xian Hu
 */
public abstract class AbstractMoveDieGameEvent extends AbstractPlayerGameEvent {

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
	public AbstractMoveDieGameEvent(Point initialPosition, Point finalPosition, String playerId) {
		super(playerId);
		this.initialPosition = initialPosition.clonePoint();
		this.finalPosition = finalPosition.clonePoint();
	}

	/**
	 * Getter of the initial position.
	 * @return the initial position.
	 */
	public Point getInitialPosition() {
		return initialPosition.clonePoint();
	}

	/**
	 * Getter of the final position.
	 * @return the final position.
	 */
	public Point getFinalPosition() {
		return finalPosition.clonePoint();
	}

}
