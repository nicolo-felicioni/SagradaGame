package it.polimi.se2018.observer.game;


import it.polimi.se2018.event.game.ReconnectGameEvent;

public interface ReconnectObserver {

    /**
     * Handle a ReconnectGameEvent.
     * @param event the ReconnectGameEvent.
     */
    void handle(ReconnectGameEvent event);

}
