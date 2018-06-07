package it.polimi.se2018.observable;

import it.polimi.se2018.event.WindowPatternChosenGameEvent;
import it.polimi.se2018.observer.WindowPatternChosenObserver;

/**
 * @author davide yi xian hu
 */
public interface WindowPatternChosenObservable {

	/**
	 * Add a WindowPatternChosenObserver.
	 * @param observer the WindowPatternChosenObserver.
	 */
	void addObserver(WindowPatternChosenObserver observer);

	/**
	 * Remove a WindowPatternChosenObserver.
	 * @param observer the WindowPatternChosenObserver.
	 */
	void removeObserver(WindowPatternChosenObserver observer);

	/**
	 * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
	 * @param event the WindowPatternChosenEvent.
	 */
	void notifyObservers(WindowPatternChosenGameEvent event);

}
