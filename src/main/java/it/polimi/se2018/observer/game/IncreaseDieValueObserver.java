package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.IncreaseDieValueGameEvent;

/**
 * @author davide yi xian hu
 */
public interface IncreaseDieValueObserver {

	/**
	 * Handle a IncreaseDieValueEvent.
	 * @param event the IncreaseDieValueEvent.
	 */
	void handle(IncreaseDieValueGameEvent event);

}
