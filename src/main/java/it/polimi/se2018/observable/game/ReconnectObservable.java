package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.ReconnectGameEvent;
import it.polimi.se2018.observer.game.ReconnectObserver;

public interface ReconnectObservable {

    /**
     * Add a ReconnectObserver.
     * @param observer the ReconnectObserver.
     */
    void addObserver(ReconnectObserver observer);

    /**
     * Remove a ReconnectObserver.
     * @param observer the ReconnectObserver.
     */
    void removeObserver(ReconnectObserver observer);

    /**
     * Notify the ReconnectObservers an ReconnectGameEvent.
     * @param event the ReconnectGameEvent.
     */
    void notifyObservers(ReconnectGameEvent event);

}
