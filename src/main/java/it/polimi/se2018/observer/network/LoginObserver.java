package it.polimi.se2018.observer.network;

import it.polimi.se2018.event.network.LoginEvent;

public interface LoginObserver {

    /**
     * Handle a LoginEvent.
     * @param event the LoginEvent.
     */
    void handle(LoginEvent event);

}
