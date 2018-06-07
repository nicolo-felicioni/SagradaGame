package it.polimi.se2018.observer;

import it.polimi.se2018.event.ChooseDraftDieValueGameEvent;

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
