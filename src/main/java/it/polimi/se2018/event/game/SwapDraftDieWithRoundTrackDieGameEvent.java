package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public class SwapDraftDieWithRoundTrackDieGameEvent extends AbstractPlayerDraftedDieGameEvent {

	/**
	 * The die in the round track.
	 */
	private Die roundTrackDie;

	/**
	 * The round position.
	 */
	private int round;

	/**
	 * Constructor.
	 * @param draftDie the die in the draft pool.
	 * @param roundTrackDie the die in the round track.
	 * @param round the round position.
	 * @param playerId the player identifier.
	 */
	public SwapDraftDieWithRoundTrackDieGameEvent(Die draftDie, Die roundTrackDie, int round, String playerId) {
		super(draftDie, playerId);
		this.roundTrackDie = new Die(roundTrackDie);
		this.round = round;
	}

	/**
	 * Getter of the die in the round track.
	 * @return the die in the round track.
	 */
	public Die getRoundTrackDie() {
		return roundTrackDie.cloneDie();
	}

	/**
	 * Getter of the round position.
	 * @return the round position.
	 */
	public int getRound() {
		return round;
	}

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
