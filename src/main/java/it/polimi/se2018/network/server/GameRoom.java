package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.CommandObserver;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.command.StartGameCommand;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class GameRoom implements GameRoomInterface {

	/**
	 * If the game is already started, it's true. False otherwise.
	 */
	private boolean started;

	/**
	 * List of network controllers of the players in the game room.
	 */
	private List<SessionInterface> playerSessions;

	/**
	 * Controller.
	 */
	private Controller controller;

	/**
	 * Minimum amount of player.
	 */
	public final static int MIN_PLAYER = 2;

	/**
	 * Maximum amount of player.
	 */
	public static int MAX_PLAYER = 4;

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
	public void addPlayerSession(SessionInterface session) {
		if(this.isIn(session.getUID())){
			this.removePlayerSession(session.getUID());
		}
		playerSessions.add(session);
	}

	/**
	 * Check if a player is connected to this room.
	 * @param uid the user identifier.
	 * @return true if the player is in this room.
	 */
	public boolean isIn(String uid) {
		for(SessionInterface playerSession : playerSessions) {
			if(playerSession.getUID().equals(uid)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Disconnect a player from this game room.
	 * @param uid the player identifier.
	 */
	public void removePlayerSession(String uid) {
		for(SessionInterface playerSession : playerSessions) {
			if(playerSession.getUID().equals(uid)){
				playerSessions.remove(playerSession);
			}
		}
	}

	/**
	 * Notify a view updater.
	 *
	 * @param updater the view updater.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	public void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		for(SessionInterface playerSession : playerSessions) {
			playerSession.handle(updater);
		}
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		this.notify(updater);
	}

	//TODO javadoc
	public void startGame() {
		if(playerSessions.size() >= MIN_PLAYER){
			controller.handle(new StartGameCommand());
		}
	}

	/**
	 * Handle a command from the network.
	 *
	 * @param command the command to be executed.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		controller.handle(command);
	}
}
