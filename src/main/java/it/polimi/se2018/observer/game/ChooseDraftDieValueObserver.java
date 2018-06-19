package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.ChooseDraftDieValueGameEvent;

/**
 * @author davide yi xian hu
 */
public interface ChooseDraftDieValueObserver {

	/**
	 * Handle a ChooseDraftDieValueEvent.
	 * @param event the ChooseDraftDieValueEvent.
	 */
	void handle(ChooseDraftDieValueGameEvent event);

}
