package it.polimi.se2018.observer;

import it.polimi.se2018.event.DraftAndPlaceGameEvent;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceObserver {

	/**
	 * Handle a DraftAndPlaceEvent.
	 * @param event the DraftAndPlaceEvent.
	 */
	void handle(DraftAndPlaceGameEvent event);

}
