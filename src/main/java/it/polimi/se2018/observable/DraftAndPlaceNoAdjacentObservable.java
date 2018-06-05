package it.polimi.se2018.observable;

import it.polimi.se2018.event.DraftAndPlaceNoAdjacentEvent;
import it.polimi.se2018.observer.DraftAndPlaceNoAdjacentObserver;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceNoAdjacentObservable {


	/**
	 * Add a DraftAndPlaceNoAdjacentObserver.
	 * @param observer the DraftAndPlaceNoAdjacentObserver.
	 */
	void addObserver(DraftAndPlaceNoAdjacentObserver observer);

	/**
	 * Remove a DraftAndPlaceNoAdjacentObserver.
	 * @param observer the DraftAndPlaceNoAdjacentObserver.
	 */
	void removeObserver(DraftAndPlaceNoAdjacentObserver observer);

	/**
	 * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	void notifyObservers(DraftAndPlaceNoAdjacentEvent event);

}
