package it.polimi.se2018.observable;

import it.polimi.se2018.event.SwapDraftDieWithDiceBagDieGameEvent;
import it.polimi.se2018.observer.SwapDraftDieWithDiceBagDieObserver;

/**
 * @author davide yi xian hu
 */
public interface SwapDraftDieWithDiceBagDieObservable {

	/**
	 * Add a SwapDraftDieWithDiceBagDieObserver.
	 * @param observer the SwapDraftDieWithDiceBagDieObserver.
	 */
	void addObserver(SwapDraftDieWithDiceBagDieObserver observer);

	/**
	 * Remove a SwapDraftDieWithDiceBagDieObserver.
	 * @param observer the SwapDraftDieWithDiceBagDieObserver.
	 */
	void removeObserver(SwapDraftDieWithDiceBagDieObserver observer);

	/**
	 * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	void notifyObservers(SwapDraftDieWithDiceBagDieGameEvent event);

}
