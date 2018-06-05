package it.polimi.se2018.event;

import it.polimi.se2018.model.Die;

/**
 * @author davide yi xian hu
 */
public class SwapDraftDieWithRoundTrackDieEvent extends AbstractPlayerEvent{

	/**
	 * The die in the draft pool.
	 */
	private Die draftDie;

	/**
	 * The die in the round track.
	 */
	private Die roundTrackDie;

	/**
	 * Constructor.
	 * @param draftDie the die in the draft pool.
	 * @param roundTrackDie the die in the round track.
	 * @param playerId the player identifier.
	 */
	public SwapDraftDieWithRoundTrackDieEvent(Die draftDie, Die roundTrackDie, String playerId) {
		super(playerId);
		this.draftDie = draftDie;
		this.roundTrackDie = roundTrackDie;
	}

	/**
	 * Getter of the die in the draft pool.
	 * @return the die in the draft pool.
	 */
	public Die getDraftDie() {
		return draftDie;
	}

	/**
	 * Getter of the die in the round track.
	 * @return the die in the round track.
	 */
	public Die getRoundTrackDie() {
		return roundTrackDie;
	}
}
