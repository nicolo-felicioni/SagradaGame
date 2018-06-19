package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.observer.network.ConnectRMIObserver;
import it.polimi.se2018.observer.network.ConnectSocketObserver;
import it.polimi.se2018.observer.network.LoginObserver;
import it.polimi.se2018.view.View;

public interface GUIInterface extends View, LoginObserver, ConnectRMIObserver, ConnectSocketObserver, GameEventObserver {
}
