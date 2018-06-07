package it.polimi.se2018.observable;

import it.polimi.se2018.event.DraftAndPlaceGameEvent;
import it.polimi.se2018.observer.DraftAndPlaceObserver;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceObservable {

	/**
	 * Add a DraftAndPlaceObserver.
	 * @param observer the DraftAndPlaceObserver.
	 */
	void addObserver(DraftAndPlaceObserver observer);

	/**
	 * Remove a DraftAndPlaceObserver.
	 * @param observer the DraftAndPlaceObserver.
	 */
	void removeObserver(DraftAndPlaceObserver observer);

	/**
	 * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
	 * @param event the DraftAndPlaceEvent.
	 */
	void notifyObservers(DraftAndPlaceGameEvent event);


}
