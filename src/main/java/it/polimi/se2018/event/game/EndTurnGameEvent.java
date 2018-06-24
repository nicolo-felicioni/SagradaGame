package it.polimi.se2018.event.game;

import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
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
