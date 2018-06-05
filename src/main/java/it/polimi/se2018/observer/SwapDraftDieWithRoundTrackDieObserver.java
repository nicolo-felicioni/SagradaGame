package it.polimi.se2018.observer;

import it.polimi.se2018.event.SwapDraftDieWithRoundTrackDieEvent;

/**
 * @author davide yi xian hu
 */
public interface SwapDraftDieWithRoundTrackDieObserver {

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	void handle(SwapDraftDieWithRoundTrackDieEvent event);

}