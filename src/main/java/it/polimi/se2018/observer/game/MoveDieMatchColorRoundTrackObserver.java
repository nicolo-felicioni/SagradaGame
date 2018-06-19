package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.MoveDieMatchColorRoundTrackGameEvent;

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
