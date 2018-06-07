package it.polimi.se2018.observer;

import it.polimi.se2018.event.EndTurnGameEvent;

/**
 * @author davide yi xian hu
 */
public interface EndTurnObserver {

	/**
	 * Handle a EndTurnEvent.
	 * @param event the EndTurnEvent.
	 */
	void handle(EndTurnGameEvent event);

}
