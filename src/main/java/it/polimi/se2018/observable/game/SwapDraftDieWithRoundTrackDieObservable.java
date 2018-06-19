package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.SwapDraftDieWithRoundTrackDieGameEvent;
import it.polimi.se2018.observer.game.SwapDraftDieWithRoundTrackDieObserver;

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
	void notifyObservers(SwapDraftDieWithRoundTrackDieGameEvent event);

}
