package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.MoveDieMatchColorRoundTrackGameEvent;
import it.polimi.se2018.observer.game.MoveDieMatchColorRoundTrackObserver;

/**
 * @author davide yi xian hu
 */
public interface MoveDieMatchColorRoundTrackObservable {

	/**
	 * Add a MoveDieMatchColorRoundTrackObserver.
	 * @param observer the MoveDieMatchColorRoundTrackObserver.
	 */
	void addObserver(MoveDieMatchColorRoundTrackObserver observer);

	/**
	 * Remove a MoveDieMatchColorRoundTrackObserver.
	 * @param observer the MoveDieMatchColorRoundTrackObserver.
	 */
	void removeObserver(MoveDieMatchColorRoundTrackObserver observer);

	/**
	 * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	void notifyObservers(MoveDieMatchColorRoundTrackGameEvent event);

}
