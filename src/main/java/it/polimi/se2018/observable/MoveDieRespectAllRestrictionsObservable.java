package it.polimi.se2018.observable;

import it.polimi.se2018.event.MoveDieRespectAllRestrictionsEvent;
import it.polimi.se2018.observer.MoveDieRespectAllRestrictionsObserver;

/**
 * @author davide yi xian hu
 */
public interface MoveDieRespectAllRestrictionsObservable {

	/**
	 * Add a MoveDieRespectAllRestrictionObserver.
	 * @param observer the MoveDieRespectAllRestrictionObserver.
	 */
	void addObserver(MoveDieRespectAllRestrictionsObserver observer);

	/**
	 * Remove a MoveDieRespectAllRestrictionObserver.
	 * @param observer the MoveDieRespectAllRestrictionObserver.
	 */
	void removeObserver(MoveDieRespectAllRestrictionsObserver observer);

	/**
	 * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
	 * @param event the MoveDieRespectAllRestrictionEvent.
	 */
	void notifyObservers(MoveDieRespectAllRestrictionsEvent event);

}
