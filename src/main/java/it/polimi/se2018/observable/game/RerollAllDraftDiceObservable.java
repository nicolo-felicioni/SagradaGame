package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.RerollAllDraftDiceGameEvent;
import it.polimi.se2018.observer.game.RerollAllDraftDiceObserver;

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
	void notifyObservers(RerollAllDraftDiceGameEvent event);

}
