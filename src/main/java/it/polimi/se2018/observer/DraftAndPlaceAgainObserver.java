package it.polimi.se2018.observer;

import it.polimi.se2018.event.DraftAndPlaceAgainEvent;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceAgainObserver {

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	void handle(DraftAndPlaceAgainEvent event);

}
