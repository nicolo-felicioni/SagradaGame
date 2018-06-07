package it.polimi.se2018.observer;

import it.polimi.se2018.event.DecreaseDieValueGameEvent;

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
