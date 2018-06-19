package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.RerollDraftDieGameEvent;

/**
 * @author davide yi xian hu
 */
public interface RerollDraftDieObserver {

	/**
	 * Handle a RerollDraftDieEvent.
	 * @param event the RerollDraftDieEvent.
	 */
	void handle(RerollDraftDieGameEvent event);

}
