package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.UseToolCardGameEvent;
import it.polimi.se2018.observer.game.UseToolCardObserver;

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
	void notifyObservers(UseToolCardGameEvent event);

}
