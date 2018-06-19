package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.WindowPatternChosenGameEvent;

/**
 * @author davide yi xian hu
 */
public interface WindowPatternChosenObserver {

	/**
	 * Handle a WindowPatternChosenEvent.
	 * @param event the WindowPatternChosenEvent.
	 */
	void handle(WindowPatternChosenGameEvent event);

}
