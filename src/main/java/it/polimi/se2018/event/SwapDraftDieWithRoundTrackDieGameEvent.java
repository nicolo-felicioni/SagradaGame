package it.polimi.se2018.event;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public class SwapDraftDieWithRoundTrackDieGameEvent extends AbstractPlayerGameEvent {

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
	public SwapDraftDieWithRoundTrackDieGameEvent(Die draftDie, Die roundTrackDie, String playerId) {
		super(playerId);
		this.draftDie = new Die(draftDie);
		this.roundTrackDie = roundTrackDie; //TODO manca il clone
	}

	/**
	 * Getter of the die in the draft pool.
	 * @return the die in the draft pool.
	 */
	public Die getDraftDie() {
		return new Die(draftDie);
	}

	/**
	 * Getter of the die in the round track.
	 * @return the die in the round track.
	 */
	public Die getRoundTrackDie() {
		return roundTrackDie;
	} //TODO manca il clone

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
