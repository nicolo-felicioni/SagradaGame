package it.polimi.se2018.observer;

import it.polimi.se2018.event.MoveDieIgnoreColorRestrictionEvent;

/**
 * @author davide yi xian hu
 */
public interface MoveDieIgnoreColorRestrictionObserver {

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	void handle(MoveDieIgnoreColorRestrictionEvent event);

}
