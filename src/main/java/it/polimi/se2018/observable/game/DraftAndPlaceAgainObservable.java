package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.DraftAndPlaceAgainGameEvent;
import it.polimi.se2018.observer.game.DraftAndPlaceAgainObserver;

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
