package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.ChooseDraftDieValueGameEvent;
import it.polimi.se2018.observer.game.ChooseDraftDieValueObserver;

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
	void notifyObservers(ChooseDraftDieValueGameEvent event);

}
