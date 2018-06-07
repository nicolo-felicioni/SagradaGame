package it.polimi.se2018.observer;

import it.polimi.se2018.event.RerollAllDraftDiceGameEvent;

/**
 * @author davide yi xian hu
 */
public interface RerollAllDraftDiceObserver {

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 * @param event the RerollAllDraftDiceEvent.
	 */
	void handle(RerollAllDraftDiceGameEvent event);

}
