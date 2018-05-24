package it.polimi.se2018.network;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.ServerSessionController;

import java.util.List;

/**
 * @author davide yi xian hu
 */
public class GameRoom {

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

}
