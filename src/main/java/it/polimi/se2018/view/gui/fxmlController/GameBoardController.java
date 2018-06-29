package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.controller.factory.WindowPatternFactory;
import it.polimi.se2018.event.network.ConnectRMIEvent;
import it.polimi.se2018.event.network.ConnectSocketEvent;
import it.polimi.se2018.event.network.LoginEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.Space;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observable.game.GameEventObservableImpl;
import it.polimi.se2018.observer.network.ConnectRMIObserver;
import it.polimi.se2018.observer.network.ConnectSocketObserver;
import it.polimi.se2018.observer.network.LoginObserver;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameBoardController extends GameEventObservableImpl implements GUILoginControllerInterface {
    private List<LoginObserver> loginObservers= new ArrayList<>();
    private List<ConnectRMIObserver> connectRMIObservers= new ArrayList<>();
    private List<ConnectSocketObserver> connectSocketObservers = new ArrayList<>();

    private static final int ROW_NUMBER= 5;
    private static final int COLUMN_NUMBER= 4;
    @FXML
    GridPane windowPattern;
    @FXML
    GridPane draftPool;

    public GameBoardController(){
        try {
            showWindowPattern(windowPattern, new WindowPatternFactory().getWindowPattern());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private void showWindowPattern(GridPane whichWindowPattern, WindowPattern windowPattern) throws MalformedURLException {
        Space[][] spaces= windowPattern.getAllSpaces();
        for (int i=0;i<COLUMN_NUMBER;i++){
            for (int j=0;j<ROW_NUMBER;j++){
                URL url=new URL("/resources/images/Die/"+spaces[i][j].getColorRestriction()+spaces[i][j].getValueRestriction()+".jpg");
                ImageView imageView=new ImageView(String.valueOf(url));
                whichWindowPattern.add(imageView,i,j);
                whichWindowPattern.setFillHeight(imageView,true);
                whichWindowPattern.setFillWidth(imageView,true);
            }
        }
    }
    private void showDraftPool(DraftPool draftPool) throws MalformedURLException {
        List<Die> draft= draftPool.getAllDice();
        int i=0;
        for (Die die:draft){
            URL url=new URL("/resources/images/Die/"+die.getColor().toString()+die.getValue().toString()+".jpg");
            ImageView imageView=new ImageView(String.valueOf(url));
            this.draftPool.addColumn(i,imageView);
            this.draftPool.setFillHeight(imageView,true);
            this.draftPool.setFillWidth(imageView,true);
            imageView.setOnMouseClicked(event -> {});
        }
    }

    public void chooseDieFromDraftPool(){

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
            System.out.println(o);
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
