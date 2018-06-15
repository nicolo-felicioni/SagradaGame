package it.polimi.se2018.observer.network;


import it.polimi.se2018.event.network.ConnectRMIEvent;

public interface ConnectRMIObserver {

    /**
     * Handle a ConnectRMIEvent.
     * @param event the ConnectRMIEvent.
     */
    void handle(ConnectRMIEvent event);
}
