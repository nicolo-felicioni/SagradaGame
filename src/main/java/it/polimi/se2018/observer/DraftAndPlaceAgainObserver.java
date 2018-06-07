package it.polimi.se2018.observer;

import it.polimi.se2018.event.DraftAndPlaceAgainGameEvent;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceAgainObserver {

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	void handle(DraftAndPlaceAgainGameEvent event);

}
