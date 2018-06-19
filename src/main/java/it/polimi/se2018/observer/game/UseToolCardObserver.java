package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.UseToolCardGameEvent;

/**
 * @author davide yi xian hu
 */
public interface UseToolCardObserver {

	/**
	 * Handle a UseToolCardEvent.
	 * @param event the UseToolCardEvent.
	 */
	void handle(UseToolCardGameEvent event);

}
