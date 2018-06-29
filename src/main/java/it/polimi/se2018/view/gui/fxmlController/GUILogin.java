package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.network.ConnectRMIEvent;
import it.polimi.se2018.event.network.ConnectSocketEvent;
import it.polimi.se2018.event.network.LoginEvent;
import it.polimi.se2018.observable.game.GameEventObservableImpl;
import it.polimi.se2018.observer.network.ConnectRMIObserver;
import it.polimi.se2018.observer.network.ConnectSocketObserver;
import it.polimi.se2018.observer.network.LoginObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUILogin extends GameEventObservableImpl implements GUILoginControllerInterface {
    private List<LoginObserver> loginObservers= new ArrayList<>();
    private List<ConnectRMIObserver> connectRMIObservers= new ArrayList<>();
    private List<ConnectSocketObserver> connectSocketObservers = new ArrayList<>();

    @FXML
    TextField accountText;
    @FXML
    TextField portText;
    @FXML
    TextField adressText;
    @FXML
    ChoiceBox networkType;
    @FXML
    BorderPane borderPane;
    @FXML
    Button loginButton;

    @FXML
    public void logInAction(ActionEvent event) throws IOException {
        if (networkType.getValue().equals("RMI")){
            notifyObservers(new ConnectRMIEvent(adressText.getText(),Integer.parseInt(portText.getText())));
        }else{
            notifyObservers(new ConnectSocketEvent(adressText.getText(),Integer.parseInt(portText.getText())));
        }
        notifyObservers(new LoginEvent(accountText.getText()));
    }

    /**
     * Add a ConnectRMIObserver.
     *
     * @param observer the ConnectRMIObserver.
     */
    @Override
    public void addObserver(ConnectRMIObserver observer) {
        connectRMIObservers.add(observer);
    }

    /**
     * Remove a ConnectRMIObserver.
     *
     * @param observer the ConnectRMIObserver.
     */
    @Override
    public void removeObserver(ConnectRMIObserver observer) {
        connectRMIObservers.remove(observer);
    }

    /**
     * Notify the ConnectRMIObservers an ConnectRMIEvent.
     *
     * @param event the ConnectRMIEvent.
     */
    @Override
    public void notifyObservers(ConnectRMIEvent event) {
        for(ConnectRMIObserver o : connectRMIObservers) {
            o.handle(event);
        }
    }

    /**
     * Add a LoginObserver.
     *
     * @param observer the LoginObserver.
     */
    @Override
    public void addObserver(LoginObserver observer) {
        loginObservers.add(observer);
    }

    /**
     * Remove a LoginObserver.
     *
     * @param observer the LoginObserver.
     */
    @Override
    public void removeObserver(LoginObserver observer) {
        loginObservers.remove(observer);
    }

    /**
     * Notify the LoginObservers an LoginEvent.
     *
     * @param event the LoginEvent.
     */
    @Override
    public void notifyObservers(LoginEvent event) {
        this.loginObservers.forEach(o -> o.handle(event));
    }

    /**
     * Add a ConnectSocketObserver.
     *
     * @param observer the ConnectSocketObserver.
     */
    @Override
    public void addObserver(ConnectSocketObserver observer) {
        connectSocketObservers.add(observer);
    }

    /**
     * Remove a ConnectSocketObserver.
     *
     * @param observer the ConnectSocketObserver.
     */
    @Override
    public void removeObserver(ConnectSocketObserver observer) {
        connectSocketObservers.remove(observer);
    }

    /**
     * Notify the ConnectSocketObservers an ConnectSocketEvent.
     *
     * @param event the ConnectSocketEvent.
     */
    @Override
    public void notifyObservers(ConnectSocketEvent event) {
        for(ConnectSocketObserver o : connectSocketObservers) {
            o.handle(event);
        }
    }

    public void addGUI(GUIInterface gui) {
        this.connectRMIObservers.add(gui);
        this.loginObservers.add(gui);
    }
}
