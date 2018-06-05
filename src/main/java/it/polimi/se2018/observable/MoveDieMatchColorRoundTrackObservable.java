package it.polimi.se2018.observable;

import it.polimi.se2018.event.MoveDieMatchColorRoundTrackEvent;
import it.polimi.se2018.observer.MoveDieMatchColorRoundTrackObserver;

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
	void notifyObservers(MoveDieMatchColorRoundTrackEvent event);

}
