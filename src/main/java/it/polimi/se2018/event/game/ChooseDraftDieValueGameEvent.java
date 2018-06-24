package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieValue;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public class ChooseDraftDieValueGameEvent extends AbstractPlayerGameEvent {

	/**
	 * The die of the draft pool.
	 */
	private Die draftedDie;

	/**
	 * The die value to assign.
	 */
	private DieValue value;

	/**
	 * Constructor.
	 * @param draftedDie the die of the draft pool.
	 * @param playerId the player identifier.
	 * @param value the value to assign to the die.
	 */
	public ChooseDraftDieValueGameEvent(Die draftedDie, DieValue value, String playerId) {
		super(playerId);
		this.draftedDie = new Die(draftedDie);
		this.value = value;
	}

	/**
	 * Getter of the drafted die from the draft pool.
	 * @return the drafted die from the draft pool.
	 */
	public Die getDraftedDie() {
		return new Die(draftedDie);
	}

	/**
	 * Getter of the die value.
	 * @return the die value.
	 */
	public DieValue getValue() {
		return value;
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
