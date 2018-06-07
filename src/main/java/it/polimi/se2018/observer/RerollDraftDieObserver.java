package it.polimi.se2018.observer;

import it.polimi.se2018.event.RerollDraftDieGameEvent;

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
