package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.DraftAndPlaceNoAdjacentGameEvent;

/**
 * @author davide yi xian hu
 */
public interface DraftAndPlaceNoAdjacentObserver {

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	void handle(DraftAndPlaceNoAdjacentGameEvent event);

}
