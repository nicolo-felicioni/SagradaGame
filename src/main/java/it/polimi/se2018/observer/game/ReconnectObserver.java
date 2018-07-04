package it.polimi.se2018.observer.game;


import it.polimi.se2018.event.network.ReconnectGameEvent;

public interface ReconnectObserver {

    /**
     * Handle a ReconnectGameEvent.
     * @param event the ReconnectGameEvent.
     */
    void handle(ReconnectGameEvent event);

}
