package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.FlipDraftDieGameEvent;

/**
 * @author davide yi xian hu
 */
public interface FlipDraftDieObserver {

	/**
	 * Handle a FlipDraftDieEvent.
	 * @param event the FlipDraftDieEvent.
	 */
	void handle(FlipDraftDieGameEvent event);


}
