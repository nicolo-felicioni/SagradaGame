package it.polimi.se2018.event;

import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public class EndTurnGameEvent extends AbstractPlayerGameEvent {

	/**
	 * Constructor.
	 * @param id the identifier of the player.
	 */
	public EndTurnGameEvent(String id) {
		super(id);
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
