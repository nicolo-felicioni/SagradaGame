package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandObserver;
import it.polimi.se2018.controller.ViewUpdaterObservable;
import it.polimi.se2018.network.utils.NetworkCommandObservable;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

/**
 * @author davide yi xian hu
 */
public interface ClientSessionControllerInterface extends NetworkCommandObservable, NetworkViewUpdaterObserver, CommandObserver, ViewUpdaterObservable {
}
