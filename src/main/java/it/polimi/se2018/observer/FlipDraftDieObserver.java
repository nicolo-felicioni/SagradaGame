package it.polimi.se2018.observer;

import it.polimi.se2018.event.FlipDraftDieEvent;

/**
 * @author davide yi xian hu
 */
public interface FlipDraftDieObserver {

	/**
	 * Handle a FlipDraftDieEvent.
	 * @param event the FlipDraftDieEvent.
	 */
	void handle(FlipDraftDieEvent event);


}
