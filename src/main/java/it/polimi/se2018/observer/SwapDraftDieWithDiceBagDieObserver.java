package it.polimi.se2018.observer;

import it.polimi.se2018.event.SwapDraftDieWithDiceBagDieGameEvent;

/**
 * @author davide yi xian hu
 */
public interface SwapDraftDieWithDiceBagDieObserver {

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	void handle(SwapDraftDieWithDiceBagDieGameEvent event);

}
