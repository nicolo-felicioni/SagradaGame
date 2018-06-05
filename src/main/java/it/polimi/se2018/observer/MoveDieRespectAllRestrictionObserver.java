package it.polimi.se2018.observer;

import it.polimi.se2018.event.MoveDieRespectAllRestrictionsEvent;

/**
 * @author davide yi xian hu
 */
public interface MoveDieRespectAllRestrictionObserver {

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	void handle(MoveDieRespectAllRestrictionsEvent event);

}
