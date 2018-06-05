package it.polimi.se2018.observable;

import it.polimi.se2018.event.DecreaseDieValueEvent;
import it.polimi.se2018.observer.DecreaseDieValueObserver;

/**
 * @author davide yi xian hu
 */
public interface DecreaseDieValueObservable {


	/**
	 * Add a DecreaseDieValueObserver.
	 * @param observer the DecreaseDieValueObserver.
	 */
	void addObserver(DecreaseDieValueObserver observer);

	/**
	 * Remove a DecreaseDieValueObserver.
	 * @param observer the DecreaseDieValueObserver.
	 */
	void removeObserver(DecreaseDieValueObserver observer);

	/**
	 * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
	 * @param event the DecreaseDieValueEvent.
	 */
	void notifyObservers(DecreaseDieValueEvent event);

}
