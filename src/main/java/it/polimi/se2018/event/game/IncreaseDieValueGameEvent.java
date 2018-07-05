package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public class IncreaseDieValueGameEvent extends AbstractPlayerDraftedDieGameEvent {

	/**
	 * Constructor.
	 * @param draftedDie the die of the draft pool.
	 * @param playerId the player identifier.
	 */
	public IncreaseDieValueGameEvent(Die draftedDie, String playerId) {
		super(draftedDie, playerId);
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
