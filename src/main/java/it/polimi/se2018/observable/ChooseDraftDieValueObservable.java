package it.polimi.se2018.observable;

import it.polimi.se2018.event.ChooseDraftDieValueEvent;
import it.polimi.se2018.observer.ChooseDraftDieValueObserver;

/**
 * @author davide yi xian hu
 */
public interface ChooseDraftDieValueObservable {

	/**
	 * Add a ChooseDraftDieValueObserver.
	 * @param observer the ChooseDraftDieValueObserver.
	 */
	void addObserver(ChooseDraftDieValueObserver observer);

	/**
	 * Remove a ChooseDraftDieValueObserver.
	 * @param observer the ChooseDraftDieValueObserver.
	 */
	void removeObserver(ChooseDraftDieValueObserver observer);

	/**
	 * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
	 * @param event the ChooseDraftDieValueEvent.
	 */
	void notifyObservers(ChooseDraftDieValueEvent event);

}
