package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.event.network.ConnectRMIEvent;
import it.polimi.se2018.event.network.ConnectSocketEvent;
import it.polimi.se2018.event.network.LoginEvent;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.rmi.RMIClient;
import it.polimi.se2018.network.socket.SocketClient;
import it.polimi.se2018.observer.game.*;
import it.polimi.se2018.observer.network.ConnectRMIObserver;
import it.polimi.se2018.observer.network.ConnectSocketObserver;
import it.polimi.se2018.observer.network.LoginObserver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Gao PeiQing
 */
public class GUIController extends Application implements GUIInterface{

    private ClientInterface client;
    private List<Player> players;
    private Player player;
    private WindowPattern[] patterns;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private PrivateObjectiveCard privateObjectiveCard;
    private ToolCard[] toolCards;
    private PublicObjectiveCard[] publicObjectiveCards;

    private List<ChooseDraftDieValueObserver> chooseDraftDieValueObservers;
    private List<DecreaseDieValueObserver> decreaseDieValueObservers;
    private List<DraftAndPlaceAgainObserver> draftAndPlaceAgainObservers;
    private List<DraftAndPlaceNoAdjacentObserver> draftAndPlaceNoAdjacentObservers;
    private List<DraftAndPlaceObserver> draftAndPlaceObservers;
    private List<EndTurnObserver> endTurnObservers;
    private List<FlipDraftDieObserver> flipDraftDieObservers;
    private List<IncreaseDieValueObserver> increaseDieValueObservers;
    private List<MoveDieIgnoreColorRestrictionObserver> moveDieIgnoreColorRestrictionObservers;
    private List<MoveDieIgnoreValueRestrictionObserver> moveDieIgnoreValueRestrictionObservers;
    private List<MoveDieMatchColorRoundTrackObserver> moveDieMatchColorRoundTrackObservers;
    private List<MoveDieRespectAllRestrictionsObserver> moveDieRespectAllRestrictionsObservers;
    private List<ReconnectObserver> reconnectObservers;
    private List<RerollAllDraftDiceObserver> rerollAllDraftDiceObservers;
    private List<RerollDraftDieObserver> rerollDraftDieObservers;
    private List<StartGameObserver> startGameObservers;
    private List<SwapDraftDieWithDiceBagDieObserver> swapDraftDieWithDiceBagDieObservers;
    private List<SwapDraftDieWithRoundTrackDieObserver> swapDraftDieWithRoundTrackDieObservers;
    private List<UseToolCardObserver> useToolCardObservers;
    private List<WindowPatternChosenObserver> windowPatternChosenObservers;

    private Stage primaryStage;

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
     * Initialize the application.
     */
    @Override
    public void init() {
        client=null;
        players= new ArrayList<Player>();
        patterns= new WindowPattern[Player.N_WINDOW_PATTERNS];
        roundTrack= null;
        draftPool=null;
        privateObjectiveCard=null;
        toolCards=new ToolCard[Model.SET_OF_TOOL_CARDS_SIZE];
        publicObjectiveCards= new PublicObjectiveCard[Model.SET_OF_PUBLIC_OBJECTIVE_CARDS_SIZE];
        this.chooseDraftDieValueObservers = new ArrayList<>();
        this.decreaseDieValueObservers = new ArrayList<>();
        this.draftAndPlaceAgainObservers = new ArrayList<>();
        this.draftAndPlaceNoAdjacentObservers = new ArrayList<>();
        this.draftAndPlaceObservers = new ArrayList<>();
        this.endTurnObservers = new ArrayList<>();
        this.flipDraftDieObservers = new ArrayList<>();
        this.increaseDieValueObservers = new ArrayList<>();
        this.moveDieIgnoreColorRestrictionObservers = new ArrayList<>();
        this.moveDieIgnoreValueRestrictionObservers = new ArrayList<>();
        this.moveDieMatchColorRoundTrackObservers = new ArrayList<>();
        this.moveDieRespectAllRestrictionsObservers = new ArrayList<>();
        this.reconnectObservers = new ArrayList<>();
        this.rerollAllDraftDiceObservers = new ArrayList<>();
        this.rerollDraftDieObservers = new ArrayList<>();
        this.startGameObservers = new ArrayList<>();
        this.swapDraftDieWithDiceBagDieObservers = new ArrayList<>();
        this.swapDraftDieWithRoundTrackDieObservers = new ArrayList<>();
        this.useToolCardObservers = new ArrayList<>();
        this.windowPatternChosenObservers = new ArrayList<>();
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

    }

    @Override
    public void updateRoundTrack(RoundTrack roundTrack) {

    }

    @Override
    public void updateDraftPool(DraftPool draftPool) {

    }

    @Override
    public void updateStatePlayer(String playerId, PlayerState state) {

    }

    @Override
    public void updatePlayer(String playerId, int favorTokens, boolean connected) {
        //TODO - g
    }

    @Override
    public void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card) {

    }

    @Override
    public void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position) {
        //if it is an update of the chosen window pattern
        if (position == WindowPatternPosition.CHOSEN) {

            //UPDATE OF THE LIST OF THE PLAYERS
            this.players.stream().filter(p -> p.getId().equals(playerId))
                    .findAny().ifPresent(p -> p.setChosenPattern(windowPattern));

            //UPDATE OF MY PLAYER
            if (playerId.equals(this.player.getId()))
                this.player.setChosenPattern(windowPattern);


        } else //if it is an update of the initial set of windows
            if (this.player.getId().equals(playerId)) {//if it is this player's window
                patterns[position.toInt()] = windowPattern;
            }
    }

    @Override
    public void updatePublicObjectiveCard(PublicObjectiveCard card, CardPosition position) {
        //TODO - G
    }

    @Override
    public void updateErrorMessage(String playerId, String message) {
        //TODO G
    }

    /**
     * Handle the view updater.
     *
     * @param updater the updater that have to be notified to the observer.
     */
    @Override
    public void handle(ViewUpdaterInterface updater) {

    }

    /**
     * Handle a ChooseDraftDieValueEvent.
     *
     * @param event the ChooseDraftDieValueEvent.
     */
    @Override
    public void handle(ChooseDraftDieValueGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a DecreaseDieValueEvent.
     *
     * @param event the DecreaseDieValueEvent.
     */
    @Override
    public void handle(DecreaseDieValueGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a DraftAndPlaceAgainEvent.
     *
     * @param event the DraftAndPlaceAgainEvent.
     */
    @Override
    public void handle(DraftAndPlaceAgainGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a DraftAndPlaceNoAdjacentEvent.
     *
     * @param event the DraftAndPlaceNoAdjacentEvent.
     */
    @Override
    public void handle(DraftAndPlaceNoAdjacentGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a DraftAndPlaceEvent.
     *
     * @param event the DraftAndPlaceEvent.
     */
    @Override
    public void handle(DraftAndPlaceGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a EndTurnEvent.
     *
     * @param event the EndTurnEvent.
     */
    @Override
    public void handle(EndTurnGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a FlipDraftDieEvent.
     *
     * @param event the FlipDraftDieEvent.
     */
    @Override
    public void handle(FlipDraftDieGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a IncreaseDieValueEvent.
     *
     * @param event the IncreaseDieValueEvent.
     */
    @Override
    public void handle(IncreaseDieValueGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a MoveDieIgnoreColorRestrictionEvent.
     *
     * @param event the MoveDieIgnoreColorRestrictionEvent.
     */
    @Override
    public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a MoveDieIgnoreValueRestrictionEvent.
     *
     * @param event the MoveDieIgnoreValueRestrictionEvent.
     */
    @Override
    public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a MoveDieMatchColorRoundTrackEvent.
     *
     * @param event the MoveDieMatchColorRoundTrackEvent.
     */
    @Override
    public void handle(MoveDieMatchColorRoundTrackGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a MoveDieRespectAllRestrictionsEvent.
     *
     * @param event the MoveDieRespectAllRestrictionsEvent.
     */
    @Override
    public void handle(MoveDieRespectAllRestrictionsGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a RerollAllDraftDiceEvent.
     *
     * @param event the RerollAllDraftDiceEvent.
     */
    @Override
    public void handle(RerollAllDraftDiceGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a RerollDraftDieEvent.
     *
     * @param event the RerollDraftDieEvent.
     */
    @Override
    public void handle(RerollDraftDieGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a StartGameEvent.
     *
     * @param event the StartGameEvent.
     */
    @Override
    public void handle(StartGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a SwapDraftDieWithDiceBagDieEvent.
     *
     * @param event the SwapDraftDieWithDiceBagDieEvent.
     */
    @Override
    public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a SwapDraftDieWithRoundTrackDieEvent.
     *
     * @param event the SwapDraftDieWithRoundTrackDieEvent.
     */
    @Override
    public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a UseToolCardEvent.
     *
     * @param event the UseToolCardEvent.
     */
    @Override
    public void handle(UseToolCardGameEvent event) {
        this.notifyObservers(event);
    }

    /**
     * Handle a WindowPatternChosenEvent.
     *
     * @param event the WindowPatternChosenEvent.
     */
    @Override
    public void handle(WindowPatternChosenGameEvent event) {
        this.notifyObservers(event);
    }

    @Override
    public void handle(LoginEvent event){
        try {
            this.client.login(event.getUsername());
            this.showChooseWindowPatternScene();
        } catch (LoginException e) {

        } catch (IOException e) {
            e.printStackTrace();
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
        addGameObserver(this.client);
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
        addGameObserver(this.client);
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
     * Add a GameEventObserver.
     *
     * @param observer the GameEventObserver.
     */
    public void addGameObserver(GameEventObserver observer) {
        this.chooseDraftDieValueObservers.add(observer);
        this.decreaseDieValueObservers.add(observer);
        this.draftAndPlaceAgainObservers.add(observer);
        this.draftAndPlaceNoAdjacentObservers.add(observer);
        this.draftAndPlaceObservers.add(observer);
        this.endTurnObservers.add(observer);
        this.flipDraftDieObservers.add(observer);
        this.increaseDieValueObservers.add(observer);
        this.moveDieIgnoreColorRestrictionObservers.add(observer);
        this.moveDieIgnoreValueRestrictionObservers.add(observer);
        this.moveDieMatchColorRoundTrackObservers.add(observer);
        this.moveDieRespectAllRestrictionsObservers.add(observer);
        this.reconnectObservers.add(observer);
        this.rerollAllDraftDiceObservers.add(observer);
        this.rerollDraftDieObservers.add(observer);
        this.startGameObservers.add(observer);
        this.swapDraftDieWithDiceBagDieObservers.add(observer);
        this.swapDraftDieWithRoundTrackDieObservers.add(observer);
        this.useToolCardObservers.add(observer);
        this.windowPatternChosenObservers.add(observer);
    }

    /**
     * Remove a GameEventObserver.
     *
     * @param observer the GameEventObserver.
     */
    public void removeGameObserver(GameEventObserver observer) {
        this.chooseDraftDieValueObservers.remove(observer);
        this.decreaseDieValueObservers.remove(observer);
        this.draftAndPlaceAgainObservers.remove(observer);
        this.draftAndPlaceNoAdjacentObservers.remove(observer);
        this.draftAndPlaceObservers.remove(observer);
        this.endTurnObservers.remove(observer);
        this.flipDraftDieObservers.remove(observer);
        this.increaseDieValueObservers.remove(observer);
        this.moveDieIgnoreColorRestrictionObservers.remove(observer);
        this.moveDieIgnoreValueRestrictionObservers.remove(observer);
        this.moveDieMatchColorRoundTrackObservers.remove(observer);
        this.moveDieRespectAllRestrictionsObservers.remove(observer);
        this.reconnectObservers.remove(observer);
        this.rerollAllDraftDiceObservers.remove(observer);
        this.rerollDraftDieObservers.remove(observer);
        this.startGameObservers.remove(observer);
        this.swapDraftDieWithDiceBagDieObservers.remove(observer);
        this.swapDraftDieWithRoundTrackDieObservers.remove(observer);
        this.useToolCardObservers.remove(observer);
        this.windowPatternChosenObservers.remove(observer);
    }

    /**
     * Add a ChooseDraftDieValueObserver.
     *
     * @param observer the ChooseDraftDieValueObserver.
     */
    @Override
    public void addObserver(ChooseDraftDieValueObserver observer) {
        this.chooseDraftDieValueObservers.add(observer);
    }

    /**
     * Remove a ChooseDraftDieValueObserver.
     *
     * @param observer the ChooseDraftDieValueObserver.
     */
    @Override
    public void removeObserver(ChooseDraftDieValueObserver observer) {
        this.chooseDraftDieValueObservers.remove(observer);
    }

    /**
     * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
     *
     * @param event the ChooseDraftDieValueEvent.
     */
    @Override
    public void notifyObservers(ChooseDraftDieValueGameEvent event) {
        this.chooseDraftDieValueObservers.forEach(chooseDraftDieValueObserver -> chooseDraftDieValueObserver.handle(event));
    }

    /**
     * Add a DecreaseDieValueObserver.
     *
     * @param observer the DecreaseDieValueObserver.
     */
    @Override
    public void addObserver(DecreaseDieValueObserver observer) {
        this.decreaseDieValueObservers.add(observer);
    }

    /**
     * Remove a DecreaseDieValueObserver.
     *
     * @param observer the DecreaseDieValueObserver.
     */
    @Override
    public void removeObserver(DecreaseDieValueObserver observer) {
        this.decreaseDieValueObservers.remove(observer);
    }

    /**
     * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
     *
     * @param event the DecreaseDieValueEvent.
     */
    @Override
    public void notifyObservers(DecreaseDieValueGameEvent event) {
        this.decreaseDieValueObservers.forEach(decreaseDieValueObserver -> decreaseDieValueObserver.handle(event));
    }

    /**
     * Add a DraftAndPlaceAgainObserver.
     *
     * @param observer the DraftAndPlaceAgainObserver.
     */
    @Override
    public void addObserver(DraftAndPlaceAgainObserver observer) {
        this.draftAndPlaceAgainObservers.add(observer);
    }

    /**
     * Remove a DraftAndPlaceAgainObserver.
     *
     * @param observer the DraftAndPlaceAgainObserver.
     */
    @Override
    public void removeObserver(DraftAndPlaceAgainObserver observer) {
        this.draftAndPlaceAgainObservers.remove(observer);
    }

    /**
     * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
     *
     * @param event the DraftAndPlaceAgainEvent.
     */
    @Override
    public void notifyObservers(DraftAndPlaceAgainGameEvent event) {
        this.draftAndPlaceAgainObservers.forEach(draftAndPlaceAgainObserver -> draftAndPlaceAgainObserver.handle(event));
    }

    /**
     * Add a DraftAndPlaceNoAdjacentObserver.
     *
     * @param observer the DraftAndPlaceNoAdjacentObserver.
     */
    @Override
    public void addObserver(DraftAndPlaceNoAdjacentObserver observer) {
        this.draftAndPlaceNoAdjacentObservers.add(observer);
    }

    /**
     * Remove a DraftAndPlaceNoAdjacentObserver.
     *
     * @param observer the DraftAndPlaceNoAdjacentObserver.
     */
    @Override
    public void removeObserver(DraftAndPlaceNoAdjacentObserver observer) {
        this.draftAndPlaceNoAdjacentObservers.add(observer);
    }

    /**
     * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
     *
     * @param event the DraftAndPlaceNoAdjacentEvent.
     */
    @Override
    public void notifyObservers(DraftAndPlaceNoAdjacentGameEvent event) {
        this.draftAndPlaceNoAdjacentObservers.forEach(draftAndPlaceNoAdjacentObserver -> draftAndPlaceNoAdjacentObserver.handle(event));
    }

    /**
     * Add a DraftAndPlaceObserver.
     *
     * @param observer the DraftAndPlaceObserver.
     */
    @Override
    public void addObserver(DraftAndPlaceObserver observer) {
        this.draftAndPlaceObservers.add(observer);
    }

    /**
     * Remove a DraftAndPlaceObserver.
     *
     * @param observer the DraftAndPlaceObserver.
     */
    @Override
    public void removeObserver(DraftAndPlaceObserver observer) {
        this.draftAndPlaceObservers.add(observer);
    }

    /**
     * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
     *
     * @param event the DraftAndPlaceEvent.
     */
    @Override
    public void notifyObservers(DraftAndPlaceGameEvent event) {
        this.draftAndPlaceObservers.forEach(draftAndPlaceObserver -> draftAndPlaceObserver.handle(event));
    }

    /**
     * Add a EndTurnObserver.
     *
     * @param observer the EndTurnObserver.
     */
    @Override
    public void addObserver(EndTurnObserver observer) {
        this.endTurnObservers.add(observer);
    }

    /**
     * Remove a EndTurnObserver.
     *
     * @param observer the EndTurnObserver.
     */
    @Override
    public void removeObserver(EndTurnObserver observer) {
        this.endTurnObservers.add(observer);
    }

    /**
     * Notify the EndTurnObservers an EndTurnEvent.
     *
     * @param event the EndTurnEvent.
     */
    @Override
    public void notifyObservers(EndTurnGameEvent event) {
        this.endTurnObservers.forEach(endTurnObserver -> endTurnObserver.handle(event));
    }

    /**
     * Add a FlipDraftDieObserver.
     *
     * @param observer the FlipDraftDieObserver.
     */
    @Override
    public void addObserver(FlipDraftDieObserver observer) {
        this.flipDraftDieObservers.add(observer);
    }

    /**
     * Remove a FlipDraftDieObserver.
     *
     * @param observer the FlipDraftDieObserver.
     */
    @Override
    public void removeObserver(FlipDraftDieObserver observer) {
        this.flipDraftDieObservers.add(observer);
    }

    /**
     * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
     *
     * @param event the FlipDraftDieEvent.
     */
    @Override
    public void notifyObservers(FlipDraftDieGameEvent event) {
        this.flipDraftDieObservers.forEach(flipDraftDieObserver -> flipDraftDieObserver.handle(event));
    }

    /**
     * Add a IncreaseDieValueObserver.
     *
     * @param observer the IncreaseDieValueObserver.
     */
    @Override
    public void addObserver(IncreaseDieValueObserver observer) {
        this.increaseDieValueObservers.add(observer);
    }

    /**
     * Remove a IncreaseDieValueObserver.
     *
     * @param observer the IncreaseDieValueObserver.
     */
    @Override
    public void removeObserver(IncreaseDieValueObserver observer) {
        this.increaseDieValueObservers.add(observer);
    }

    /**
     * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
     *
     * @param event the IncreaseDieValueEvent.
     */
    @Override
    public void notifyObservers(IncreaseDieValueGameEvent event) {
        this.increaseDieValueObservers.forEach(increaseDieValueObserver -> increaseDieValueObserver.handle(event));
    }

    /**
     * Add a MoveDieIgnoreColorRestrictionObserver.
     *
     * @param observer the MoveDieIgnoreColorRestrictionObserver.
     */
    @Override
    public void addObserver(MoveDieIgnoreColorRestrictionObserver observer) {
        this.moveDieIgnoreColorRestrictionObservers.add(observer);
    }

    /**
     * Remove a MoveDieIgnoreColorRestrictionObserver.
     *
     * @param observer the MoveDieIgnoreColorRestrictionObserver.
     */
    @Override
    public void removeObserver(MoveDieIgnoreColorRestrictionObserver observer) {
        this.moveDieIgnoreColorRestrictionObservers.add(observer);
    }

    /**
     * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
     *
     * @param event the MoveDieIgnoreColorRestrictionEvent.
     */
    @Override
    public void notifyObservers(MoveDieIgnoreColorRestrictionGameEvent event) {
        this.moveDieIgnoreColorRestrictionObservers.forEach(moveDieIgnoreColorRestrictionObserver -> moveDieIgnoreColorRestrictionObserver.handle(event));
    }

    /**
     * Add a MoveDieIgnoreValueRestrictionObserver.
     *
     * @param observer the MoveDieIgnoreValueRestrictionObserver.
     */
    @Override
    public void addObserver(MoveDieIgnoreValueRestrictionObserver observer) {
        this.moveDieIgnoreValueRestrictionObservers.add(observer);
    }

    /**
     * Remove a MoveDieIgnoreValueRestrictionObserver.
     *
     * @param observer the MoveDieIgnoreValueRestrictionObserver.
     */
    @Override
    public void removeObserver(MoveDieIgnoreValueRestrictionObserver observer) {
        this.moveDieIgnoreValueRestrictionObservers.add(observer);
    }

    /**
     * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
     *
     * @param event the MoveDieIgnoreValueRestrictionEvent.
     */
    @Override
    public void notifyObservers(MoveDieIgnoreValueRestrictionGameEvent event) {
        this.moveDieIgnoreValueRestrictionObservers.forEach(moveDieIgnoreValueRestrictionObserver -> moveDieIgnoreValueRestrictionObserver.handle(event));
    }

    /**
     * Add a MoveDieMatchColorRoundTrackObserver.
     *
     * @param observer the MoveDieMatchColorRoundTrackObserver.
     */
    @Override
    public void addObserver(MoveDieMatchColorRoundTrackObserver observer) {
        this.moveDieMatchColorRoundTrackObservers.add(observer);
    }

    /**
     * Remove a MoveDieMatchColorRoundTrackObserver.
     *
     * @param observer the MoveDieMatchColorRoundTrackObserver.
     */
    @Override
    public void removeObserver(MoveDieMatchColorRoundTrackObserver observer) {
        this.moveDieMatchColorRoundTrackObservers.add(observer);
    }

    /**
     * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
     *
     * @param event the MoveDieMatchColorRoundTrackEvent.
     */
    @Override
    public void notifyObservers(MoveDieMatchColorRoundTrackGameEvent event) {
        this.moveDieMatchColorRoundTrackObservers.forEach(moveDieMatchColorRoundTrackObserver -> moveDieMatchColorRoundTrackObserver.handle(event));
    }

    /**
     * Add a MoveDieRespectAllRestrictionObserver.
     *
     * @param observer the MoveDieRespectAllRestrictionObserver.
     */
    @Override
    public void addObserver(MoveDieRespectAllRestrictionsObserver observer) {
        this.moveDieRespectAllRestrictionsObservers.add(observer);
    }

    /**
     * Remove a MoveDieRespectAllRestrictionObserver.
     *
     * @param observer the MoveDieRespectAllRestrictionObserver.
     */
    @Override
    public void removeObserver(MoveDieRespectAllRestrictionsObserver observer) {
        this.moveDieRespectAllRestrictionsObservers.add(observer);
    }

    /**
     * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
     *
     * @param event the MoveDieRespectAllRestrictionEvent.
     */
    @Override
    public void notifyObservers(MoveDieRespectAllRestrictionsGameEvent event) {
        this.moveDieRespectAllRestrictionsObservers.forEach(moveDieRespectAllRestrictionsObserver -> moveDieRespectAllRestrictionsObserver.handle(event));
    }

    /**
     * Add a RerollAllDraftDiceObserver.
     *
     * @param observer the RerollAllDraftDiceObserver.
     */
    @Override
    public void addObserver(RerollAllDraftDiceObserver observer) {
        this.rerollAllDraftDiceObservers.add(observer);
    }

    /**
     * Remove a RerollAllDraftDiceObserver.
     *
     * @param observer the RerollAllDraftDiceObserver.
     */
    @Override
    public void removeObserver(RerollAllDraftDiceObserver observer) {
        this.rerollAllDraftDiceObservers.add(observer);
    }

    /**
     * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
     *
     * @param event the RerollAllDraftDiceEvent.
     */
    @Override
    public void notifyObservers(RerollAllDraftDiceGameEvent event) {
        this.rerollAllDraftDiceObservers.forEach(rerollAllDraftDiceObserver -> rerollAllDraftDiceObserver.handle(event));
    }

    /**
     * Add a RerollDraftDieObserver.
     *
     * @param observer the RerollDraftDieObserver.
     */
    @Override
    public void addObserver(RerollDraftDieObserver observer) {
        this.rerollDraftDieObservers.add(observer);
    }

    /**
     * Remove a RerollDraftDieObserver.
     *
     * @param observer the RerollDraftDieObserver.
     */
    @Override
    public void removeObserver(RerollDraftDieObserver observer) {
        this.rerollDraftDieObservers.add(observer);
    }

    /**
     * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
     *
     * @param event the RerollDraftDieEvent.
     */
    @Override
    public void notifyObservers(RerollDraftDieGameEvent event) {
        this.rerollDraftDieObservers.forEach(rerollDraftDieObserver -> rerollDraftDieObserver.handle(event));
    }

    /**
     * Add a SwapDraftDieWithDiceBagDieObserver.
     *
     * @param observer the SwapDraftDieWithDiceBagDieObserver.
     */
    @Override
    public void addObserver(SwapDraftDieWithDiceBagDieObserver observer) {
        this.swapDraftDieWithDiceBagDieObservers.add(observer);
    }

    /**
     * Remove a SwapDraftDieWithDiceBagDieObserver.
     *
     * @param observer the SwapDraftDieWithDiceBagDieObserver.
     */
    @Override
    public void removeObserver(SwapDraftDieWithDiceBagDieObserver observer) {
        this.swapDraftDieWithDiceBagDieObservers.add(observer);
    }

    /**
     * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
     *
     * @param event the SwapDraftDieWithDiceBagDieEvent.
     */
    @Override
    public void notifyObservers(SwapDraftDieWithDiceBagDieGameEvent event) {
        this.swapDraftDieWithDiceBagDieObservers.forEach(swapDraftDieWithDiceBagDieObserver -> swapDraftDieWithDiceBagDieObserver.handle(event));
    }

    /**
     * Add a SwapDrafDieWithRoundTrackDieObserver.
     *
     * @param observer the SwapDrafDieWithRoundTrackDieObserver.
     */
    @Override
    public void addObserver(SwapDraftDieWithRoundTrackDieObserver observer) {
        this.swapDraftDieWithRoundTrackDieObservers.add(observer);
    }

    /**
     * Remove a SwapDrafDieWithRoundTrackDieObserver.
     *
     * @param observer the SwapDrafDieWithRoundTrackDieObserver.
     */
    @Override
    public void removeObserver(SwapDraftDieWithRoundTrackDieObserver observer) {
        this.swapDraftDieWithRoundTrackDieObservers.add(observer);
    }

    /**
     * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
     *
     * @param event the SwapDrafDieWithRoundTrackDieEvent.
     */
    @Override
    public void notifyObservers(SwapDraftDieWithRoundTrackDieGameEvent event) {
        this.swapDraftDieWithRoundTrackDieObservers.forEach(swapDraftDieWithRoundTrackDieObserver -> swapDraftDieWithRoundTrackDieObserver.handle(event));
    }

    /**
     * Add a UseToolCardObserver.
     *
     * @param observer the UseToolCardObserver.
     */
    @Override
    public void addObserver(UseToolCardObserver observer) {
        this.useToolCardObservers.add(observer);
    }

    /**
     * Remove a UseToolCardObserver.
     *
     * @param observer the UseToolCardObserver.
     */
    @Override
    public void removeObserver(UseToolCardObserver observer) {
        this.useToolCardObservers.add(observer);
    }

    /**
     * Notify the UseToolCardObservers an UseToolCardEvent.
     *
     * @param event the UseToolCardEvent.
     */
    @Override
    public void notifyObservers(UseToolCardGameEvent event) {
        this.useToolCardObservers.forEach(useToolCardObserver -> useToolCardObserver.handle(event));
    }

    /**
     * Add a WindowPatternChosenObserver.
     *
     * @param observer the WindowPatternChosenObserver.
     */
    @Override
    public void addObserver(WindowPatternChosenObserver observer) {
        this.windowPatternChosenObservers.add(observer);
    }

    /**
     * Remove a WindowPatternChosenObserver.
     *
     * @param observer the WindowPatternChosenObserver.
     */
    @Override
    public void removeObserver(WindowPatternChosenObserver observer) {
        this.windowPatternChosenObservers.add(observer);
    }

    /**
     * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
     *
     * @param event the WindowPatternChosenEvent.
     */
    @Override
    public void notifyObservers(WindowPatternChosenGameEvent event) {
        this.windowPatternChosenObservers.forEach(windowPatternChosenObserver -> windowPatternChosenObserver.handle(event));
    }

    /**
     * Add a StartGameObserver.
     *
     * @param observer the StartGameObserver.
     */
    @Override
    public void addObserver(StartGameObserver observer) {
        this.startGameObservers.add(observer);
    }

    /**
     * Remove a StartGameObserver.
     *
     * @param observer the StartGameObserver.
     */
    @Override
    public void removeObserver(StartGameObserver observer) {
        this.startGameObservers.remove(observer);
    }

    /**
     * Notify the StartGameObserver an StartGameEvent.
     *
     * @param event the StartGameEvent.
     */
    @Override
    public void notifyObservers(StartGameEvent event) {
        this.startGameObservers.forEach(o -> o.handle(event));
    }

    /**
     * Add a ReconnectObserver.
     *
     * @param observer the ReconnectObserver.
     */
    @Override
    public void addObserver(ReconnectObserver observer) {
        this.reconnectObservers.add(observer);
    }

    /**
     * Remove a ReconnectObserver.
     *
     * @param observer the ReconnectObserver.
     */
    @Override
    public void removeObserver(ReconnectObserver observer) {
        this.reconnectObservers.remove(observer);
    }

    /**
     * Notify the ReconnectObservers an ReconnectGameEvent.
     *
     * @param event the ReconnectGameEvent.
     */
    @Override
    public void notifyObservers(ReconnectGameEvent event) {
        this.reconnectObservers.forEach(o -> o.handle(event));
    }

    /**
     * Show the login scene.
     * @throws IOException if the application can not load the fxml file.
     */
    private void showLoginScene() throws IOException{
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
    }

    /**
     * Show the choose window pattern scene.
     * @throws IOException if the application can not load the fxml file.
     */
    private void showChooseWindowPatternScene() throws IOException{
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
    }
}
