package it.polimi.se2018.observable;

import it.polimi.se2018.event.IncreaseDieValueEvent;
import it.polimi.se2018.observer.IncreaseDieValueObserver;

/**
 * @author davide yi xian hu
 */
public interface IncreaseDieValueObservable {


	/**
	 * Add a IncreaseDieValueObserver.
	 * @param observer the IncreaseDieValueObserver.
	 */
	void addObserver(IncreaseDieValueObserver observer);

	/**
	 * Remove a IncreaseDieValueObserver.
	 * @param observer the IncreaseDieValueObserver.
	 */
	void removeObserver(IncreaseDieValueObserver observer);

	/**
	 * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
	 * @param event the IncreaseDieValueEvent.
	 */
	void notifyObservers(IncreaseDieValueEvent event);


}
