package it.polimi.se2018.observable;

import it.polimi.se2018.event.DraftAndPlaceAgainGameEvent;
import it.polimi.se2018.observer.DraftAndPlaceAgainObserver;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceAgainObservable {

	/**
	 * Add a DraftAndPlaceAgainObserver.
	 * @param observer the DraftAndPlaceAgainObserver.
	 */
	void addObserver(DraftAndPlaceAgainObserver observer);

	/**
	 * Remove a DraftAndPlaceAgainObserver.
	 * @param observer the DraftAndPlaceAgainObserver.
	 */
	void removeObserver(DraftAndPlaceAgainObserver observer);

	/**
	 * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	void notifyObservers(DraftAndPlaceAgainGameEvent event);

}
