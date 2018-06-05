package it.polimi.se2018.observer;

import it.polimi.se2018.event.UseToolCardEvent;

/**
 * @author davide yi xian hu
 */
public interface UseToolCardObserver {

	/**
	 * Handle a UseToolCardEvent.
	 * @param event the UseToolCardEvent.
	 */
	void handle(UseToolCardEvent event);

}
