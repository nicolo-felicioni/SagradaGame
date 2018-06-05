package it.polimi.se2018.observable;

import it.polimi.se2018.event.UseToolCardEvent;
import it.polimi.se2018.observer.UseToolCardObserver;

/**
 * @author davide yi xian hu
 */
public interface UseToolCardObservable {

	/**
	 * Add a UseToolCardObserver.
	 * @param observer the UseToolCardObserver.
	 */
	void addObserver(UseToolCardObserver observer);

	/**
	 * Remove a UseToolCardObserver.
	 * @param observer the UseToolCardObserver.
	 */
	void removeObserver(UseToolCardObserver observer);

	/**
	 * Notify the UseToolCardObservers an UseToolCardEvent.
	 * @param event the UseToolCardEvent.
	 */
	void notifyObservers(UseToolCardEvent event);

}
