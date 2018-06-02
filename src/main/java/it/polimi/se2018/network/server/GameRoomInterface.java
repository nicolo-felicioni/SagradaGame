package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.CommandObservable;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObservable;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

/**
 * @author davide yi xian hu
 */
public interface GameRoomInterface extends NetworkViewUpdaterObserver, NetworkCommandObserver{
}
