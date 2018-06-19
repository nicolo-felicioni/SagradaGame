package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.MoveDieIgnoreValueRestrictionGameEvent;
import it.polimi.se2018.observer.game.MoveDieIgnoreValueRestrictionObserver;

/**
 * @author davide yi xian hu
 */
public interface MoveDieIgnoreValueRestrictionObservable {

	/**
	 * Add a MoveDieIgnoreValueRestrictionObserver.
	 * @param observer the MoveDieIgnoreValueRestrictionObserver.
	 */
	void addObserver(MoveDieIgnoreValueRestrictionObserver observer);

	/**
	 * Remove a MoveDieIgnoreValueRestrictionObserver.
	 * @param observer the MoveDieIgnoreValueRestrictionObserver.
	 */
	void removeObserver(MoveDieIgnoreValueRestrictionObserver observer);

	/**
	 * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	void notifyObservers(MoveDieIgnoreValueRestrictionGameEvent event);


}
