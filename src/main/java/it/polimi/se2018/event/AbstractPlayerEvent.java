package it.polimi.se2018.event;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;

/**
 * @author davide yi xian hu
 */
public abstract class AbstractPlayerEvent implements Event {

	/**
	 * The identifier of the player.
	 */
	private String playerId;

	/**
	 * Constructor.
	 * @param id the identifier of the player.
	 */
	public AbstractPlayerEvent(String id) {
		this.playerId = id;
	}

	/**
	 * Getter of the identifier of the player.
	 * @return the identifier of the player.
	 */
	public String getPlayerId() {
		return playerId;
	}
}
