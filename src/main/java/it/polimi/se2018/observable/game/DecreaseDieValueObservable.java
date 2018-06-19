package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.DecreaseDieValueGameEvent;
import it.polimi.se2018.observer.game.DecreaseDieValueObserver;

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
	void notifyObservers(DecreaseDieValueGameEvent event);

}
