package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.event.network.ConnectRMIEvent;
import it.polimi.se2018.event.network.ConnectSocketEvent;
import it.polimi.se2018.event.network.LoginEvent;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.rmi.RMIClient;
import it.polimi.se2018.network.socket.SocketClient;
import it.polimi.se2018.observable.game.GameEventObservableImpl;
import it.polimi.se2018.observer.network.ConnectRMIObserver;
import it.polimi.se2018.observer.network.ConnectSocketObserver;
import it.polimi.se2018.observer.network.LoginObserver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.List;

/**
 * Author Gao PeiQing
 */
public class GUIController extends Application implements GUIInterface{

    private ClientInterface client;

    private String playerId;

    private GameEventObservableImpl observable;

    private Stage primaryStage;

    /**
     * The gui choose window pattern controller.
     */
    private GUIChooseWindowPattern guiChooseWindowPatternController;

    /**
     * The gui choose window pattern scene.
     */
    private Scene guiChooseWindowPatternScene;

    /**
     * The gui game controller.
     */
    private GUIGame guiGameController;

    /**
     * The gui game scene.
     */
    private Scene guiGameScene;

    /**
     * Initialize the application.
     */
    @Override
    public void init() {
        this.guiChooseWindowPatternController = null;
        this.guiGameController = null;
        this.observable = new GameEventObservableImpl();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIGame.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            guiGameController = loader.getController();
            guiGameScene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIChooseWindowPattern.fxml"));
            root = loader.load();
            guiChooseWindowPatternController = loader.getController();
            guiChooseWindowPatternScene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * Start the application.
     * @param primaryStage the primary stage.
     * @throws IOException if the application can not open the fxml file.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        showLoginScene();
    }


    /**
     * Run the application.
     */
    public void run() {
        Application.launch(new String[0]);
    }


    @Override
    public void updateMoveDieFromDraftToWindow(Point p, Die draftedDie, String playerId) {

    }

    @Override
    public void updateToolCard(ToolCard toolCard, int number) {
        Platform.runLater(
                () -> {
                    if (guiGameController != null && this.playerId.equals(playerId)) {
                        guiGameController.setToolCard(toolCard, CardPosition.fromInt(number));
                    }
                }
        );

    }

    @Override
    public void updateRoundTrack(RoundTrack roundTrack) {
        Platform.runLater(
                () -> {
                    if (guiGameController != null && this.playerId.equals(playerId)) {
                        guiGameController.setRoundtrack(roundTrack);
                    }
                }
        );
    }

    @Override
    public void updateDraftPool(DraftPool draftPool) {
        Platform.runLater(
                () -> {
                    if (guiGameController != null && this.playerId.equals(playerId)) {
                        guiGameController.setDraftpool(draftPool);
                    }
                }
        );

    }

    @Override
    public void updateStatePlayer(String playerId, PlayerState state) {
        Platform.runLater(
                () -> {
                    if (guiGameController != null && this.playerId.equals(playerId)) {
                        guiGameController.setPlayerState(state);
                    }
                }
        );
    }

    @Override
    public void updatePlayer(String playerId, int favorTokens, boolean connected) {
        Platform.runLater(
                () -> {
                    if (guiGameController != null && this.playerId.equals(playerId)) {
                        Player player = new Player(playerId);
                        player.setTokens(favorTokens);
                        player.setConnected(connected);
                        guiGameController.setPlayer(player);
                    }
                }
        );
    }

    @Override
    public void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card) {
        Platform.runLater(
                () -> {
                    if (guiChooseWindowPatternController != null && this.playerId.equals(playerId)) {
                        guiChooseWindowPatternController.setPrivateObjectiveCard(card);
                    }
                    if (guiGameController != null && this.playerId.equals(playerId)) {
                        guiGameController.setPrivateObjectiveCard(card);
                    }
                }
        );

    }

    @Override
    public void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position) {
        Platform.runLater(
                () -> {
                    if (guiChooseWindowPatternController != null && this.playerId.equals(playerId)) {
                        guiChooseWindowPatternController.setWindowPattern(windowPattern.cloneWindowPattern(), position);
                    }
                    if (guiGameController != null && this.playerId.equals(playerId) && position == WindowPatternPosition.CHOSEN) {
                        guiGameController.setWindowpattern(windowPattern);
                    }
                }
        );

    }

    @Override
    public void updatePublicObjectiveCard(PublicObjectiveCard card, CardPosition position) {
        Platform.runLater(
                () -> {
                    if (guiGameController != null && this.playerId.equals(playerId)) {
                        guiGameController.setPublicCard(card, position);
                    }
                }
        );
    }

    @Override
    public void updateErrorMessage(String playerId, String message) {
        //TODO G
    }

    @Override
    public void updateEndGame(List<RankingPlayer> rankingPlayers) {
        //todo g
    }

    /**
     * Handle the view updater.
     *
     * @param updater the updater that have to be notified to the observer.
     */
    @Override
    public void handle(ViewUpdaterInterface updater) {
        updater.update(this);
    }

    /**
     * Handle a ChooseDraftDieValueEvent.
     *
     * @param event the ChooseDraftDieValueEvent.
     */
    @Override
    public void handle(ChooseDraftDieValueGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a DecreaseDieValueEvent.
     *
     * @param event the DecreaseDieValueEvent.
     */
    @Override
    public void handle(DecreaseDieValueGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a DraftAndPlaceAgainEvent.
     *
     * @param event the DraftAndPlaceAgainEvent.
     */
    @Override
    public void handle(DraftAndPlaceAgainGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a DraftAndPlaceNoAdjacentEvent.
     *
     * @param event the DraftAndPlaceNoAdjacentEvent.
     */
    @Override
    public void handle(DraftAndPlaceNoAdjacentGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a DraftAndPlaceEvent.
     *
     * @param event the DraftAndPlaceEvent.
     */
    @Override
    public void handle(DraftAndPlaceGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a EndTurnEvent.
     *
     * @param event the EndTurnEvent.
     */
    @Override
    public void handle(EndTurnGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a FlipDraftDieEvent.
     *
     * @param event the FlipDraftDieEvent.
     */
    @Override
    public void handle(FlipDraftDieGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a IncreaseDieValueEvent.
     *
     * @param event the IncreaseDieValueEvent.
     */
    @Override
    public void handle(IncreaseDieValueGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a MoveDieIgnoreColorRestrictionEvent.
     *
     * @param event the MoveDieIgnoreColorRestrictionEvent.
     */
    @Override
    public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a MoveDieIgnoreValueRestrictionEvent.
     *
     * @param event the MoveDieIgnoreValueRestrictionEvent.
     */
    @Override
    public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a MoveDieMatchColorRoundTrackEvent.
     *
     * @param event the MoveDieMatchColorRoundTrackEvent.
     */
    @Override
    public void handle(MoveDieMatchColorRoundTrackGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a MoveDieRespectAllRestrictionsEvent.
     *
     * @param event the MoveDieRespectAllRestrictionsEvent.
     */
    @Override
    public void handle(MoveDieRespectAllRestrictionsGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a RerollAllDraftDiceEvent.
     *
     * @param event the RerollAllDraftDiceEvent.
     */
    @Override
    public void handle(RerollAllDraftDiceGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a RerollDraftDieEvent.
     *
     * @param event the RerollDraftDieEvent.
     */
    @Override
    public void handle(RerollDraftDieGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a StartGameEvent.
     *
     * @param event the StartGameEvent.
     */
    @Override
    public void handle(StartGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a SwapDraftDieWithDiceBagDieEvent.
     *
     * @param event the SwapDraftDieWithDiceBagDieEvent.
     */
    @Override
    public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a SwapDraftDieWithRoundTrackDieEvent.
     *
     * @param event the SwapDraftDieWithRoundTrackDieEvent.
     */
    @Override
    public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a UseToolCardEvent.
     *
     * @param event the UseToolCardEvent.
     */
    @Override
    public void handle(UseToolCardGameEvent event) {
        this.observable.notifyObservers(event);
    }

    /**
     * Handle a WindowPatternChosenEvent.
     *
     * @param event the WindowPatternChosenEvent.
     */
    @Override
    public void handle(WindowPatternChosenGameEvent event) {
        this.showGame();
        this.observable.notifyObservers(event);
    }

    @Override
    public void handle(LoginEvent event){
        try {
            this.client.login(event.getUsername());
            this.playerId = event.getUsername();
            this.showChooseWindowPatternScene();
        } catch (LoginException e) {

        }
    }

    /**
     * Handle a ConnectRMIEvent.
     *
     * @param event the ConnectRMIEvent.
     */
    @Override
    public void handle(ConnectRMIEvent event) {
        this.client= new RMIClient(this);
        this.observable.addGameObserver(this.client);
        try {
            client.connect(event.getAddress(),event.getPort());
        } catch (NetworkException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle a ConnectSocketEvent.
     *
     * @param event the ConnectSocketEvent.
     */
    @Override
    public void handle(ConnectSocketEvent event) {
        this.client=new SocketClient(this);
        this.observable.addGameObserver(this.client);
        try {
            client.connect(event.getAddress(),event.getPort());
        } catch (NetworkException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle a ReconnectGameEvent.
     *
     * @param event the ReconnectGameEvent.
     */
    @Override
    public void handle(ReconnectGameEvent event) {
        //TODO
    }

    /**
     * Show the login scene.
     * @throws IOException if the application can not load the fxml file.
     */
    private void showLoginScene() throws IOException{
  //      /*
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUILogin.fxml"));
        Parent root = loader.load();
        GUILogin controller = loader.getController();
        controller.addGameObserver(this);
        controller.addObserver((ConnectRMIObserver) this);
        controller.addObserver((ConnectSocketObserver) this);
        controller.addObserver((LoginObserver) this);
        primaryStage.setTitle("Sagrada-The Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
   //     */

        /*
        GUIDraftPool dp = new GUIDraftPool();
        DraftPool draftPool = new DraftPool();
        draftPool.addDie(new die(DieColor.BLUE, DieValue.ONE));
        draftPool.addDie(new die(DieColor.RED, DieValue.ONE));
        draftPool.addDie(new die(DieColor.GREEN, DieValue.ONE));
        draftPool.addDie(new die(DieColor.PURPLE, DieValue.ONE));
        draftPool.addDie(new die(DieColor.YELLOW, DieValue.ONE));
        draftPool.addDie(new die(DieColor.BLUE, DieValue.ONE));
        draftPool.addDie(new die(DieColor.BLUE, DieValue.ONE));
        draftPool.addDie(new die(DieColor.BLUE, DieValue.ONE));
        dp.setDraftPool(draftPool);
        */

/*
        GUIWindowPattern wp = new GUIWindowPattern();
        wp.setWindowPattern(new WindowPatternFactory().getWindowPattern());
*/

        /*
        GUISpace s= null;
        try {
            Space space = new ColorSpace(DieColor.BLUE);
            space.placeDie(new Die(DieColor.BLUE, DieValue.ONE));
            s = new GUISpace(space,  new Point(0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */


        /*
        GUIRoundTrack rt = new GUIRoundTrack();
        RoundTrack roundTrack = new RoundTrack();
        List<Die> dice1 = new ArrayList<>();
        dice1.add(new Die(DieColor.BLUE, DieValue.ONE));
        dice1.add(new Die(DieColor.RED, DieValue.ONE));
        dice1.add(new Die(DieColor.BLUE, DieValue.TWO));
        dice1.add(new Die(DieColor.YELLOW, DieValue.ONE));
        roundTrack.addDice(dice1);
        List<Die> dice2 = new ArrayList<>();
        dice2.add(new Die(DieColor.GREEN, DieValue.SIX));
        dice2.add(new Die(DieColor.PURPLE, DieValue.FIVE));
        dice2.add(new Die(DieColor.GREEN, DieValue.FOUR));
        dice2.add(new Die(DieColor.YELLOW, DieValue.THREE));
        dice2.add(new Die(DieColor.GREEN, DieValue.FOUR));
        dice2.add(new Die(DieColor.YELLOW, DieValue.THREE));
        roundTrack.addDice(dice2);
        List<Die> dice3 = new ArrayList<>();
        dice3.add(new Die(DieColor.RED, DieValue.ONE));
        dice3.add(new Die(DieColor.BLUE, DieValue.TWO));
        dice3.add(new Die(DieColor.GREEN, DieValue.SIX));
        dice3.add(new Die(DieColor.BLUE, DieValue.FIVE));
        dice3.add(new Die(DieColor.RED, DieValue.FOUR));
        roundTrack.addDice(dice3);
        rt.setRoundTrack(roundTrack);
        */

        /*
        GUIToolCard tc = new GUIToolCard(new ToolCardsFactory().drawCard());
        */

        /*
        GUIPrivateObjectiveCard pc = new GUIPrivateObjectiveCard(new PrivateObjectiveCardsFactory().drawCard());
        */



        /*
        Scene scene = new Scene(s, 172, 258);
        scene.getStylesheets().add("css/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        */

    }

    /**
     * Show the choose window pattern scene.
     */
    private void showChooseWindowPatternScene(){
        Platform.runLater(
                () -> {
                    guiChooseWindowPatternController.setObserver(this);
                    guiChooseWindowPatternController.setPlayerId(playerId);
                    primaryStage.setScene(guiChooseWindowPatternScene);
                    primaryStage.show();
                }
        );

    }


    /**
     * Show the choose window pattern scene.
     */
    private void showGame(){
        Platform.runLater(
                () -> {
                    guiGameController.setPlayerId(playerId);
                    guiGameController.setObserver(this);
                    primaryStage.setScene(guiGameScene);
                    primaryStage.show();
                }
        );

    }

}
