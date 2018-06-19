package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.MoveDieIgnoreColorRestrictionGameEvent;
import it.polimi.se2018.observer.game.MoveDieIgnoreColorRestrictionObserver;

/**
 * @author davide yi xian hu
 */
public interface MoveDieIgnoreColorRestrictionObservable {

	/**
	 * Add a MoveDieIgnoreColorRestrictionObserver.
	 * @param observer the MoveDieIgnoreColorRestrictionObserver.
	 */
	void addObserver(MoveDieIgnoreColorRestrictionObserver observer);

	/**
	 * Remove a MoveDieIgnoreColorRestrictionObserver.
	 * @param observer the MoveDieIgnoreColorRestrictionObserver.
	 */
	void removeObserver(MoveDieIgnoreColorRestrictionObserver observer);

	/**
	 * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	void notifyObservers(MoveDieIgnoreColorRestrictionGameEvent event);


}
