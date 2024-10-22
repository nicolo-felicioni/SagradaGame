package it.polimi.se2018.network.socket;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.event.network.DisconnectEvent;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.json.Json;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.observable.game.GameEventObservableImpl;
import it.polimi.se2018.observer.network.DisconnectObserver;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author davide yi xian hu
 */
public class SocketServerSession extends GameEventObservableImpl implements SessionInterface {

	private final Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;

	/**
	 * The disconnect event observers.
	 */
	private List<DisconnectObserver> disconnectObservers;

	/**
	 * Unique identifier of the client.
	 */
	private String uid;

	/**
	 * Constructor.
	 * @param socket the socket connection with the client.
	 */
	public SocketServerSession(Socket socket) {
		this.disconnectObservers = new ArrayList<>();
		this.socket = socket;
		try {
			this.outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			this.outStream.flush();
			this.inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			new Thread(new NetworkListener(this)).start();
		} catch (IOException e) {
			MyLog.getMyLog().log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public String getUID() {
		return uid;
	}

	/**
	 * Send a message to the client.
	 * @param text the message to be sent.
	 */
	private void send(String text) {
		try {
			this.outStream.writeUTF(text);
			this.outStream.flush();
		} catch (IOException e) {
			try {
				inStream.close();
				outStream.close();
				socket.close();
				notifyObservers(new DisconnectEvent(uid));
			} catch (IOException e1) {
				MyLog.getMyLog().log(Level.WARNING, e1.getMessage());
			}
			MyLog.getMyLog().log(Level.WARNING, e.getMessage());
		}
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 */
	@Override
	public synchronized void handle(ViewUpdaterInterface updater) {
		send(Json.getGson().toJson(updater, ViewUpdaterInterface.class));
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

	/**
	 * Add a DisconnectObserver.
	 *
	 * @param observer the DisconnectObserver.
	 */
	@Override
	public void addObserver(DisconnectObserver observer) {
		this.disconnectObservers.remove(observer);
	}

	/**
	 * Remove a DisconnectObserver.
	 *
	 * @param observer the DisconnectObserver.
	 */
	@Override
	public void removeObserver(DisconnectObserver observer) {
		this.disconnectObservers.remove(observer);
	}

	/**
	 * Notify the DisconnectObserver an DisconnectObserver.
	 *
	 * @param event the DisconnectEvent.
	 */
	@Override
	public void notifyObservers(DisconnectEvent event) {
		this.disconnectObservers.forEach(o -> o.handle(event));
	}

	private class NetworkListener implements Runnable {

		/**
		 * The socket server session.
		 */
		private final SocketServerSession session;

		/**
		 * Constructor.
		 * @param session the socket server session.
		 */
		NetworkListener(SocketServerSession session) {
			this.session = session;
		}

		/**
		 * If true the network will listen to network messages.
		 */
		private boolean run = true;

		@Override
		public void run() {
			try {
				uid = new LoginMessage(inStream.readUTF(), true).getUid();
				Server.getInstance().login(uid, session);
				send(new LoginResponse(true, LoginResponse.LOGIN_SUCCESS_MESSAGE).toJson());
				while(this.run) {
					Json.getGson().fromJson(inStream.readUTF(), GameEvent.class).accept(session);
				}
			} catch (IOException e) {
				try {
					inStream.close();
					outStream.close();
					socket.close();
					notifyObservers(new DisconnectEvent(uid));
					this.run = false;
				} catch (IOException e1) {
					MyLog.getMyLog().log(Level.WARNING, e1.getMessage());
				}
			} catch (LoginException e) {
				send(new LoginResponse(false, LoginResponse.LOGIN_FAIL_MESSAGE).toJson());
				try {
					uid = new LoginMessage(inStream.readUTF(), true).getUid();
					Server.getInstance().reconnect(uid, session);
					send(new LoginResponse(true, LoginResponse.LOGIN_SUCCESS_MESSAGE).toJson());
					while(this.run) {
						Json.getGson().fromJson(inStream.readUTF(), GameEvent.class).accept(session);
					}
				} catch (IOException e2) {
					try {
						inStream.close();
						outStream.close();
						socket.close();
						notifyObservers(new DisconnectEvent(uid));
						this.run = false;
					} catch (IOException e1) {
						MyLog.getMyLog().log(Level.WARNING, e1.getMessage());
					}
				} catch (LoginException e1) {
					send(new LoginResponse(false, LoginResponse.LOGIN_FAIL_MESSAGE).toJson());
				}
			}
		}

	}
}
