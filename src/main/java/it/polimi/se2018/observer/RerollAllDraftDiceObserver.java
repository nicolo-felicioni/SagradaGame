package it.polimi.se2018.observer;

import it.polimi.se2018.event.RerollAllDraftDiceEvent;

/**
 * @author davide yi xian hu
 */
public interface RerollAllDraftDiceObserver {

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 * @param event the RerollAllDraftDiceEvent.
	 */
	void handle(RerollAllDraftDiceEvent event);

}
