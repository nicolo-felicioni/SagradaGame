package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.IncreaseDieValueGameEvent;
import it.polimi.se2018.observer.game.IncreaseDieValueObserver;

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
	void notifyObservers(IncreaseDieValueGameEvent event);


}
