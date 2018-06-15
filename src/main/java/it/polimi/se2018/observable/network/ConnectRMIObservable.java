package it.polimi.se2018.observable.network;


import it.polimi.se2018.event.network.ConnectRMIEvent;
import it.polimi.se2018.observer.network.ConnectRMIObserver;

public interface ConnectRMIObservable {

    /**
     * Add a ConnectRMIObserver.
     * @param observer the ConnectRMIObserver.
     */
    void addObserver(ConnectRMIObserver observer);

    /**
     * Remove a ConnectRMIObserver.
     * @param observer the ConnectRMIObserver.
     */
    void removeObserver(ConnectRMIObserver observer);

    /**
     * Notify the ConnectRMIObservers an ConnectRMIEvent.
     * @param event the ConnectRMIEvent.
     */
    void notifyObservers(ConnectRMIEvent event);

}
