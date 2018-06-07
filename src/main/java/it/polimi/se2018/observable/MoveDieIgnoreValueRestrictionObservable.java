package it.polimi.se2018.observable;

import it.polimi.se2018.event.MoveDieIgnoreValueRestrictionGameEvent;
import it.polimi.se2018.observer.MoveDieIgnoreValueRestrictionObserver;

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
