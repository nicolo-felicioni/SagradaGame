package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.MoveDieIgnoreValueRestrictionGameEvent;

/**
 * @author davide yi xian hu
 */
public interface MoveDieIgnoreValueRestrictionObserver {

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	void handle(MoveDieIgnoreValueRestrictionGameEvent event);

}
