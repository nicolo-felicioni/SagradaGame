package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.ViewUpdaterObservable;
import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.observable.game.GameEventObservable;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public interface GameRoomInterface extends GameEventObserver, GameEventObservable, ViewUpdaterObserver, ViewUpdaterObservable {

}
