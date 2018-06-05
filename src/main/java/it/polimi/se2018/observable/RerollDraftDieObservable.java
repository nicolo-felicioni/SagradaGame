package it.polimi.se2018.observable;

import it.polimi.se2018.event.RerollDraftDieEvent;
import it.polimi.se2018.observer.RerollDraftDieObserver;

/**
 * @author davide yi xian hu
 */
public interface RerollDraftDieObservable {

	/**
	 * Add a RerollDraftDieObserver.
	 * @param observer the RerollDraftDieObserver.
	 */
	void addObserver(RerollDraftDieObserver observer);

	/**
	 * Remove a RerollDraftDieObserver.
	 * @param observer the RerollDraftDieObserver.
	 */
	void removeObserver(RerollDraftDieObserver observer);

	/**
	 * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
	 * @param event the RerollDraftDieEvent.
	 */
	void notifyObservers(RerollDraftDieEvent event);

}
