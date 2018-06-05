package it.polimi.se2018.event;

/**
 * @author davide yi xian hu
 */
public class EndTurnEvent extends AbstractPlayerEvent {

	/**
	 * Constructor.
	 * @param id the identifier of the player.
	 */
	public EndTurnEvent(String id) {
		super(id);
	}

}
