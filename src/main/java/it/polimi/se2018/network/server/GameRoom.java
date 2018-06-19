package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.controller.updater.PlayerUpdater;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.observable.game.GameEventObservableImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class GameRoom extends GameEventObservableImpl implements GameRoomInterface {

	/**
	 * If the game is already started, it's true. False otherwise.
	 */
	private boolean started;

	/**
	 * List of network controllers of the players in the game room.
	 */
	private List<SessionInterface> playerSessions;

	/**
	 * List of view updater observer.
	 */
	private List<ViewUpdaterObserver> observers;

	/**
	 * Timer.
	 */
	private Timer timer;

	/**
	 * Minimum amount of player.
	 */
	public static final int MIN_PLAYER = 2;

	/**
	 * Maximum amount of player.
	 */
	public static int MAX_PLAYER = 4;

	/**
	 * Interval of time to wait before to start a game.
	 */
	public static int TIMER_WAIT_START_GAME = 5000;

	/**
	 * Constructor.
	 */
	public GameRoom() {
		this.playerSessions = new ArrayList<>();
		this.observers = new ArrayList<>();
		Model model = new Model();
		model.addObserver(this);
		this.addGameObserver(new Controller(model));
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
		if(this.isIn(session.getUID())){
			this.removePlayerSession(session.getUID());
		}
		playerSessions.add(session);
		observers.add(session);
		playerSessions.forEach(s -> this.notifyObservers(new PlayerUpdater(session.getUID(), 0)));
		this.refreshTimer(TIMER_WAIT_START_GAME);
	}

	/**
	 * Check if a player is connected to this room.
	 * @param uid the user identifier.
	 * @return true if the player is in this room.
	 */
	public synchronized boolean isIn(String uid) {
		return playerSessions.stream().anyMatch(s -> s.getUID().equals(uid));
	}

	/**
	 * Disconnect a player from this game room.
	 * @param uid the player identifier.
	 */
	private synchronized void removePlayerSession(String uid) {
		playerSessions.stream().filter(s -> s.getUID().equals(uid)).forEach(s -> observers.remove(s));
		this.refreshTimer(TIMER_WAIT_START_GAME);
	}

	/**
	 * Refresh the timer.
	 */
	private synchronized void refreshTimer(int time) {
		if(timer != null) {
			this.timer.disable();
		}
		if(this.countUniqueIdentifier() >= MIN_PLAYER) {
			System.out.println(" ==> GameRoom :: Timer started. " + TIMER_WAIT_START_GAME/1000 + " seconds countdown."); //TODO println
			this.timer = new Timer(time);
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
		this.observers.forEach(observer -> {
			new Thread(new Runnable() {
				@Override
				public void run() {
					observer.handle(updater);
				}
			}).start();});
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
	}

	/**
	 * Handle the view updater.
	 *
	 * @param updater the updater that have to be notified to the observer.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater) {
		System.out.println(" <=== GameRoom :: View Updater received. Type : " + updater.getClass().getSimpleName() + "."); //TODO println
		this.notifyObservers(updater);
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
					System.out.println(" ==> GameRoom :: Time out. Game is starting..."); //TODO println
					List<String> playerIds = new ArrayList<>();
					playerSessions.stream().map(s -> s.getUID()).distinct().forEach(id -> playerIds.add(id));
					handle(new StartGameEvent(playerIds));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
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
