package it.polimi.se2018.view.gui;

import it.polimi.se2018.observable.game.GameEventObservable;
import it.polimi.se2018.observable.network.ConnectRMIObservable;
import it.polimi.se2018.observable.network.ConnectSocketObservable;
import it.polimi.se2018.observable.network.LoginObservable;

public interface GUILoginControllerInterface extends GameEventObservable, LoginObservable, ConnectRMIObservable, ConnectSocketObservable {
}
