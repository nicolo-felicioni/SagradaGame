package it.polimi.se2018.observer;

import it.polimi.se2018.event.DecreaseDieValueEvent;

/**
 * @author davide yi xian hu
 */
public interface DecreaseDieValueObserver {

	/**
	 * Handle a DecreaseDieValueEvent.
	 * @param event the DecreaseDieValueEvent.
	 */
	void handle(DecreaseDieValueEvent event);

}
