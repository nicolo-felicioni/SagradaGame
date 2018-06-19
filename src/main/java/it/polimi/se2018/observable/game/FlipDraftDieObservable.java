package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.FlipDraftDieGameEvent;
import it.polimi.se2018.observer.game.FlipDraftDieObserver;

/**
 * @author davide yi xian hu
 */
public interface FlipDraftDieObservable {

	/**
	 * Add a FlipDraftDieObserver.
	 * @param observer the FlipDraftDieObserver.
	 */
	void addObserver(FlipDraftDieObserver observer);

	/**
	 * Remove a FlipDraftDieObserver.
	 * @param observer the FlipDraftDieObserver.
	 */
	void removeObserver(FlipDraftDieObserver observer);

	/**
	 * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
	 * @param event the FlipDraftDieEvent.
	 */
	void notifyObservers(FlipDraftDieGameEvent event);

}
