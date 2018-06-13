package it.polimi.se2018.event;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public class IncreaseDieValueGameEvent extends AbstractPlayerGameEvent {

	/**
	 * The die of the draft pool.
	 */
	private Die draftedDie;

	/**
	 * Constructor.
	 * @param draftedDie the die of the draft pool.
	 * @param playerId the player identifier.
	 */
	public IncreaseDieValueGameEvent(Die draftedDie, String playerId) {
		super(playerId);
		this.draftedDie = new Die(draftedDie);
	}

	/**
	 * Getter of the drafted die from the draft pool.
	 * @return the drafted die from the draft pool.
	 */
	public Die getDraftedDie() {
		return new Die(draftedDie);
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
