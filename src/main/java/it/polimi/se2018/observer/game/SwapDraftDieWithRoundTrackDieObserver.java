package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.SwapDraftDieWithRoundTrackDieGameEvent;

/**
 * @author davide yi xian hu
 */
public interface SwapDraftDieWithRoundTrackDieObserver {

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	void handle(SwapDraftDieWithRoundTrackDieGameEvent event);

}
