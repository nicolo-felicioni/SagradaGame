package it.polimi.se2018.observable;

import it.polimi.se2018.event.RerollAllDraftDiceEvent;
import it.polimi.se2018.observer.RerollAllDraftDiceObserver;

/**
 * @author davide yi xian hu
 */
public interface RerollAllDraftDiceObservable {

	/**
	 * Add a RerollAllDraftDiceObserver.
	 * @param observer the RerollAllDraftDiceObserver.
	 */
	void addObserver(RerollAllDraftDiceObserver observer);

	/**
	 * Remove a RerollAllDraftDiceObserver.
	 * @param observer the RerollAllDraftDiceObserver.
	 */
	void removeObserver(RerollAllDraftDiceObserver observer);

	/**
	 * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
	 * @param event the RerollAllDraftDiceEvent.
	 */
	void notifyObservers(RerollAllDraftDiceEvent event);

}
