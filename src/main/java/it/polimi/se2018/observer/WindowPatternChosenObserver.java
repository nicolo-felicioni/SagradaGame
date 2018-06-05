package it.polimi.se2018.observer;

import it.polimi.se2018.event.WindowPatternChosenEvent;

/**
 * @author davide yi xian hu
 */
public interface WindowPatternChosenObserver {

	/**
	 * Handle a WindowPatternChosenEvent.
	 * @param event the WindowPatternChosenEvent.
	 */
	void handle(WindowPatternChosenEvent event);

}
