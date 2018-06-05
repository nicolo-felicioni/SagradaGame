package it.polimi.se2018.event;

/**
 * @author davide yi xian hu
 */
public class RerollAllDraftDiceEvent extends AbstractPlayerEvent {

	/**
	 * Constructor.
	 * @param id the identifier of the player.
	 */
	public RerollAllDraftDiceEvent(String id) {
		super(id);
	}
}
