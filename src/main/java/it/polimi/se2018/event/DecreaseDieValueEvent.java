package it.polimi.se2018.event;

import it.polimi.se2018.model.Die;

/**
 * @author davide yi xian hu
 */
public class DecreaseDieValueEvent extends AbstractPlayerEvent{

	/**
	 * The die of the draft pool.
	 */
	private Die draftedDie;

	/**
	 * Constructor.
	 * @param draftedDie the die of the draft pool.
	 * @param playerId the player identifier.
	 */
	public DecreaseDieValueEvent(Die draftedDie, String playerId) {
		super(playerId);
		this.draftedDie = draftedDie;
	}

	/**
	 * Getter of the drafted die from the draft pool.
	 * @return the drafted die from the draft pool.
	 */
	public Die getDraftedDie() {
		return draftedDie;
	}

}
