package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.EndTurnGameEvent;

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
