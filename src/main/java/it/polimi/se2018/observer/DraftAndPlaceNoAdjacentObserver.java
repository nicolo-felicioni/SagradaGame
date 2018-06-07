package it.polimi.se2018.observer;

import it.polimi.se2018.event.DraftAndPlaceNoAdjacentGameEvent;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceNoAdjacentObserver {

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	void handle(DraftAndPlaceNoAdjacentGameEvent event);

}
