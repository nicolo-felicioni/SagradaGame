package it.polimi.se2018.observable.network;

import it.polimi.se2018.event.network.ConnectSocketEvent;
import it.polimi.se2018.observer.network.ConnectSocketObserver;

public interface ConnectSocketObservable {

    /**
     * Add a ConnectSocketObserver.
     * @param observer the ConnectSocketObserver.
     */
    void addObserver(ConnectSocketObserver observer);

    /**
     * Remove a ConnectSocketObserver.
     * @param observer the ConnectSocketObserver.
     */
    void removeObserver(ConnectSocketObserver observer);

    /**
     * Notify the ConnectSocketObservers an ConnectSocketEvent.
     * @param event the ConnectSocketEvent.
     */
    void notifyObservers(ConnectSocketEvent event);

}
