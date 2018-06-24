package it.polimi.se2018.event.game;

/**
 * @author Davide Yi Xian Hu
 */
public abstract class AbstractPlayerGameEvent implements GameEvent {

	/**
	 * The identifier of the player.
	 */
	private String playerId;

	/**
	 * Constructor.
	 * @param id the identifier of the player.
	 */
	public AbstractPlayerGameEvent(String id) {
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
