package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.CommandObservable;
import it.polimi.se2018.controller.CommandObserver;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.ServerSessionController;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class GameRoom implements NetworkCommandObserver, CommandObservable {

	/**
	 * If the game is already started, it's true. False otherwise.
	 */
	private boolean started;

	/**
	 * List of network controllers of the players in the game room.
	 */
	private List<ServerSessionController> playerSessions;

	/**
	 * Controller of the game.
	 */
	private Controller controller;

	/**
	 * Getter of started.
	 *
	 * @return true if the game is already started, false otherwise.
	 */
	public boolean isStarted() {
		return started;
	}


	@Override
	public void addObserver(CommandObserver o) {
		this.controller = controller;
	}

	@Override
	public void notify(CommandInterface command) {
		controller.update(command);
	}

	public void addPlayerSession(ServerSessionController session) {
		if(this.isIn(session.getUID())){
			this.removePlayerSession(session.getUID());
		}
		playerSessions.add(session);
	}

	public boolean isIn(String uid) {
		for(ServerSessionController playerSession : playerSessions) {
			if(playerSession.getUID().equals(uid)){
				return true;
			}
		}
		return false;
	}

	private void removePlayerSession(String uid) {
		for(ServerSessionController playerSession : playerSessions) {
			if(playerSession.getUID().equals(uid)){
				playerSessions.remove(playerSession);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * Handle the command. Forward it to the controller.
	 */
	@Override
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		controller.update(command);
	}
}
