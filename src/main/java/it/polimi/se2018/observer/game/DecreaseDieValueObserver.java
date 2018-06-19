package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.DecreaseDieValueGameEvent;

/**
 * @author davide yi xian hu
 */
public interface DecreaseDieValueObserver {

	/**
	 * Handle a DecreaseDieValueEvent.
	 * @param event the DecreaseDieValueEvent.
	 */
	void handle(DecreaseDieValueGameEvent event);

}
