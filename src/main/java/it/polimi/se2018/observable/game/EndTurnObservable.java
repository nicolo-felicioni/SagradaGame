package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.EndTurnGameEvent;
import it.polimi.se2018.observer.game.EndTurnObserver;

/**
 * @author davide yi xian hu
 */
public interface EndTurnObservable {

	/**
	 * Add a EndTurnObserver.
	 * @param observer the EndTurnObserver.
	 */
	void addObserver(EndTurnObserver observer);

	/**
	 * Remove a EndTurnObserver.
	 * @param observer the EndTurnObserver.
	 */
	void removeObserver(EndTurnObserver observer);

	/**
	 * Notify the EndTurnObservers an EndTurnEvent.
	 * @param event the EndTurnEvent.
	 */
	void notifyObservers(EndTurnGameEvent event);

}
