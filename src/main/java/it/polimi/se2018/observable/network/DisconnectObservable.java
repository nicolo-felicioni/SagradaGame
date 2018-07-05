package it.polimi.se2018.observable.network;

import it.polimi.se2018.event.network.DisconnectEvent;
import it.polimi.se2018.observer.network.DisconnectObserver;

public interface DisconnectObservable {

    /**
     * Add a DisconnectObserver.
     * @param observer the DisconnectObserver.
     */
    void addObserver(DisconnectObserver observer);

    /**
     * Remove a DisconnectObserver.
     * @param observer the DisconnectObserver.
     */
    void removeObserver(DisconnectObserver observer);

    /**
     * Notify the DisconnectObserver an DisconnectObserver.
     * @param event the DisconnectEvent.
     */
    void notifyObservers(DisconnectEvent event);

}
