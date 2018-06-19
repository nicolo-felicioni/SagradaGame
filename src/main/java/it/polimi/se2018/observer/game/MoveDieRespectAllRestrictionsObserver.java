package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.MoveDieRespectAllRestrictionsGameEvent;

/**
 * @author davide yi xian hu
 */
public interface MoveDieRespectAllRestrictionsObserver {

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	void handle(MoveDieRespectAllRestrictionsGameEvent event);

}
