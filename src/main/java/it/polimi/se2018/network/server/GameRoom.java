package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.event.network.DisconnectEvent;
import it.polimi.se2018.event.network.ReconnectGameEvent;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.network.ServerConfiguration;
import it.polimi.se2018.observable.game.GameEventObservableImpl;
import it.polimi.se2018.observable.game.ReconnectObservable;
import it.polimi.se2018.observer.game.ReconnectObserver;
import it.polimi.se2018.observer.network.DisconnectObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * @author Davide Yi Xian Hu
 */
public class GameRoom extends GameEventObservableImpl implements GameRoomInterface, ReconnectObservable, DisconnectObserver {

    /**
     * If the game is already started, it's true. False otherwise.
     */
    private boolean started;

    /**
     * List of network controllers of the players in the game room.
     */
    private List<SessionInterface> playerSessions;

    /**
     * List of disconnected network controllers of the players in the game room.
     */
    private List<SessionInterface> disconnectedSessions;

    /**
     * List of view updater observer.
     */
    private List<ViewUpdaterObserver> observers;


    /**
     * List of view updater observer.
     */
    private List<ReconnectObserver> reconnectObservers;

    /**
     * Timer.
     */
    private Timer timer;

    /**
     * Minimum amount of player.
     */
    private static final int MIN_PLAYER = 2;

    /**
     * Maximum amount of player.
     */
    private static final int MAX_PLAYER = 4;

    /**
     * Constructor.
     */
    public GameRoom() {
        this.playerSessions = new ArrayList<>();
        this.disconnectedSessions = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.reconnectObservers = new ArrayList<>();
        Model model = new Model();
        model.addObserver(this);
        Controller controller = new Controller(model);
        this.addGameObserver(controller);
        this.addObserver((ReconnectObserver) controller);
        controller.addObserver((ViewUpdaterObserver) this);
        controller.addObserver((DisconnectObserver) this);
        this.started = false;
    }

    /**
     * Getter of started.
     *
     * @return true if the game is already started, false otherwise.
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Connect a player session to this game room.
     * @param session the player session.
     */
    public synchronized void addPlayerSession(SessionInterface session) {
        if(this.isInAsDisconnected(session.getUID())) {
            Optional<SessionInterface> ses = disconnectedSessions.stream().filter(s -> s.getUID().equals(session.getUID())).findAny();
            if(ses.isPresent()) {
                disconnectedSessions.remove(ses.get());
                observers.remove(ses.get());
                playerSessions.add(session);
                observers.add(session);
                this.notifyObservers(new ReconnectGameEvent(session.getUID()));
            }
        }else if(!this.isIn(session.getUID())){
            playerSessions.add(session);
            observers.add(session);
            this.refreshTimer();
        }
    }

    /**
     * Check if a player is connected to this room.
     * @param uid the user identifier.
     * @return true if the player is in this room.
     */
    public synchronized boolean isIn(String uid) {
        return playerSessions.stream().anyMatch(s -> s.getUID().equals(uid)) || disconnectedSessions.stream().anyMatch(s -> s.getUID().equals(uid));
    }

    /**
     * Check if a player is connected to this room.
     * @param uid the user identifier.
     * @return true if the player is connected but in this room.
     */
    public synchronized boolean isInAsConnected(String uid) {
        return playerSessions.stream().anyMatch(s -> s.getUID().equals(uid));
    }

    /**
     * Check if a player is disconnected to this room.
     * @param uid the user identifier.
     * @return true if the player is disconnected but in this room.
     */
    public synchronized boolean isInAsDisconnected(String uid) {
        return disconnectedSessions.stream().anyMatch(s -> s.getUID().equals(uid));
    }

    /**
     * Refresh the timer.
     */
    private synchronized void refreshTimer() {
        if(timer != null) {
            this.timer.disable();
        }
        if(this.countUniqueIdentifier() == MAX_PLAYER) {
            List<String> playerIds = new ArrayList<>();
            playerSessions.stream().map(SessionInterface::getUID).distinct().forEach(id -> playerIds.add(id));
            handle(new StartGameEvent(playerIds));
        } else if(this.countUniqueIdentifier() >= MIN_PLAYER) {
            this.timer = new Timer(ServerConfiguration.getGameRoomTimer());
            new Thread(timer).start();
        }
    }

    private synchronized long countUniqueIdentifier() {
        return playerSessions.stream().map(SessionInterface::getUID).distinct().count();
    }

    /**
     * Add a view updater observer.
     *
     * @param observer the view updater observer.
     */
    @Override
    public void addObserver(ViewUpdaterObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Remove a view updater observer.
     *
     * @param observer the view updater observer.
     */
    @Override
    public void removeObserver(ViewUpdaterObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify a view updater.
     *
     * @param updater the view updater.
     */
    public void notifyObservers(ViewUpdaterInterface updater) {
        this.observers.forEach(observer -> new Thread( () -> observer.handle(updater)).start());
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

    /**
     * Handle a StartGameEvent.
     *
     * @param event the StartGameEvent.
     */
    @Override
    public void handle(StartGameEvent event) {
        this.notifyObservers(event);
        this.started = true;
    }

    /**
     * Handle the view updater.
     *
     * @param updater the updater that have to be notified to the observer.
     */
    @Override
    public void handle(ViewUpdaterInterface updater) {
        this.notifyObservers(updater);
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
     * Handle a ConnectSocketEvent.
     *
     * @param event the ConnectSocketEvent.
     */
    @Override
    public synchronized void handle(DisconnectEvent event) {
        Optional<SessionInterface> ses = playerSessions.stream().filter(s -> s.getUID().equals(event.getPlayerId())).findAny();
        if(ses.isPresent()) {
            playerSessions.remove(ses.get());
            observers.remove(ses.get());
            disconnectedSessions.add(ses.get());
        }
    }

    private class Timer implements Runnable {

        /**
         * The countdown.
         */
        private final int time;

        /**
         * If the timer is still active.
         */
        private boolean active;

        /**
         * Constructor
         * @param time the countdown time.
         */
        Timer(int time) {
            this.time = time;
            this.active = true;
        }

        @Override
        public void run() {
            try {
                if (this.time > 0) {
                    Thread.sleep(this.time);
                }
                if(isActive()) {
                    List<String> playerIds = new ArrayList<>();
                    playerSessions.stream().map(SessionInterface::getUID).distinct().forEach(id -> playerIds.add(id));
                    handle(new StartGameEvent(playerIds));
                }
            } catch (InterruptedException e) {
                MyLog.getMyLog().log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        public synchronized boolean isActive() {
            return active;
        }

        public synchronized void disable() {
            this.active = false;
        }
    }

}
