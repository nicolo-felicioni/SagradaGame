package it.polimi.se2018.observer;

import it.polimi.se2018.event.IncreaseDieValueEvent;

/**
 * @author davide yi xian hu
 */
public interface IncreaseDieValueObserver {

	/**
	 * Handle a IncreaseDieValueEvent.
	 * @param event the IncreaseDieValueEvent.
	 */
	void handle(IncreaseDieValueEvent event);

}
