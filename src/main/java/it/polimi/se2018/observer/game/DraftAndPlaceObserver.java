package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceObserver {

	/**
	 * Handle a DraftAndPlaceEvent.
	 * @param event the DraftAndPlaceEvent.
	 */
	void handle(DraftAndPlaceGameEvent event);

}
