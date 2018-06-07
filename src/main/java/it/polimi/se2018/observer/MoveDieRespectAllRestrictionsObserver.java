package it.polimi.se2018.observer;

import it.polimi.se2018.event.MoveDieRespectAllRestrictionsGameEvent;

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
