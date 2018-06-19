package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.MoveDieIgnoreColorRestrictionGameEvent;

/**
 * @author davide yi xian hu
 */
public interface MoveDieIgnoreColorRestrictionObserver {

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	void handle(MoveDieIgnoreColorRestrictionGameEvent event);

}
