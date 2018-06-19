package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.DraftAndPlaceAgainGameEvent;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceAgainObserver {

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	void handle(DraftAndPlaceAgainGameEvent event);

}
