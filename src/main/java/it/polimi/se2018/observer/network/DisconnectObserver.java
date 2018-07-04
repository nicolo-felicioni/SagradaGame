package it.polimi.se2018.observer.network;

import it.polimi.se2018.event.network.ConnectSocketEvent;
import it.polimi.se2018.event.network.DisconnectEvent;

public interface DisconnectObserver {

    /**
     * Handle a ConnectSocketEvent.
     * @param event the ConnectSocketEvent.
     */
    void handle(DisconnectEvent event);
}
