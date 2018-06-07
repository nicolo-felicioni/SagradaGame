package it.polimi.se2018.network.server;

import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;
import it.polimi.se2018.observable.GameEventObservable;
import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public interface GameRoomInterface extends GameEventObserver, GameEventObservable {
}
