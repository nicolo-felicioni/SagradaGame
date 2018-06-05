package it.polimi.se2018.observable;

import it.polimi.se2018.event.SwapDraftDieWithRoundTrackDieEvent;
import it.polimi.se2018.observer.SwapDraftDieWithRoundTrackDieObserver;

/**
 * @author davide yi xian hu
 */
public interface SwapDraftDieWithRoundTrackDieObservable {

	/**
	 * Add a SwapDrafDieWithRoundTrackDieObserver.
	 * @param observer the SwapDrafDieWithRoundTrackDieObserver.
	 */
	void addObserver(SwapDraftDieWithRoundTrackDieObserver observer);

	/**
	 * Remove a SwapDrafDieWithRoundTrackDieObserver.
	 * @param observer the SwapDrafDieWithRoundTrackDieObserver.
	 */
	void removeObserver(SwapDraftDieWithRoundTrackDieObserver observer);

	/**
	 * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
	 * @param event the SwapDrafDieWithRoundTrackDieEvent.
	 */
	void notifyObservers(SwapDraftDieWithRoundTrackDieEvent event);

}
