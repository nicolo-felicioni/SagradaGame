package it.polimi.se2018.network.server;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.network.rmi.RMIServer;
import it.polimi.se2018.network.socket.SocketServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Davide Yi Xian Hu
 */
public class Server {

	/**
	 * Instance of the singleton server.
	 */
	private static Server server = null;

	/**
	 * List of active game rooms.
	 */
	private static List<GameRoom> roomList = new ArrayList<>();

	/**
	 * Private default constructor.
	 */
	private Server() {
		new RMIServer();
		new SocketServer();
	}


	/**
	 * Server getter. The server is a singleton.
	 * @return an istance of the server.
	 */
	public static Server getInstance(){
		if(server == null) {
			server = new Server();
		}
		return server;
	}

	/**
	 * Login a client session to the server.
	 * The client will be logged to the last game room that has no started.
	 * If no game rooms are available, a new one will be created.
	 *
	 * @param uid the unique identifier of the client
	 * @param session the session between client and server.
	 * @throws LoginException if the login fails.
	 */
	public void login(String uid, SessionInterface session) throws LoginException {
		//Look for a game room where a client has already logged in.
		GameRoom room = getGameRoom(uid);
		if(room == null) {
			//Look for a game room that has not started yet.
			room = getNotStartedGameRoom();
			if(room == null) {
				//Create a new game room.
				room = new GameRoom();
				roomList.add(room);
			}
			room.addPlayerSession(session);
			session.addGameObserver(room);
		}else{
			throw new LoginException(LoginException.DEFAULT_LOGIN_ERROR_MESSAGE);
		}
	}

	/**
	 * Reconnect a client session to the server.
	 * The client will be logged to the game room he is already connected.
	 *
	 * @param uid the unique identifier of the client
	 * @param session the session between client and server.
	 * @throws LoginException if the login fails or the user identifier is not in the game rooms.
	 */
	public void reconnect(String uid, SessionInterface session) throws LoginException {
		//Look for a game room where a client has already logged in.
		GameRoom room = getGameRoom(uid);
		if(room == null) {
			throw new LoginException(LoginException.DEFAULT_LOGIN_ERROR_MESSAGE);
		}else{
			room.substitutePlayerSession(session);
			session.addGameObserver(room);
		}
	}

	/**
	 * Look for a game room that has not started. Return null if all the rooms have started.
	 *
	 * @return a game room that has not started, null if all rooms have already started.
	 */
	private GameRoom getNotStartedGameRoom(){
		Optional<GameRoom> gameRoom = roomList.stream().filter(room -> !room.isStarted()).findAny();
		if (gameRoom.isPresent()) {
			return gameRoom.get();
		} else {
			return null;
		}
	}

	/**
	 * Look for a game room that contain a player with an uid. Return null if no room has that player.
	 *
	 * @param uid the player unique identifier.
	 * @return a game room that contains the player, null if no room has that player.
	 */
	private GameRoom getGameRoom(String uid) {
		Optional<GameRoom> gameRoom = roomList.stream().filter(room -> room.isIn(uid)).findAny();
		if (gameRoom.isPresent()) {
			return gameRoom.get();
		} else {
			return null;
		}
	}

}
