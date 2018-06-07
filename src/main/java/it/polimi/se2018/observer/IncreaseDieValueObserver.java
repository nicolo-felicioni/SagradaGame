package it.polimi.se2018.observer;

import it.polimi.se2018.event.IncreaseDieValueGameEvent;

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
