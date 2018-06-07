package it.polimi.se2018.observer;

import it.polimi.se2018.event.MoveDieMatchColorRoundTrackGameEvent;

/**
 * @author davide yi xian hu
 */
public interface MoveDieMatchColorRoundTrackObserver {

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	void handle(MoveDieMatchColorRoundTrackGameEvent event);

}
