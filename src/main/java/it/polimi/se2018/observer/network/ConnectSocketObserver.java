package it.polimi.se2018.observer.network;

import it.polimi.se2018.event.network.ConnectSocketEvent;

public interface ConnectSocketObserver {

    /**
     * Handle a ConnectSocketEvent.
     * @param event the ConnectSocketEvent.
     */
    void handle(ConnectSocketEvent event);

}
