package it.polimi.se2018.network.server;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.rmi.RMIServer;
import it.polimi.se2018.network.socket.SocketServer;

import java.rmi.RemoteException;
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
	 * RMI Server.
	 */
	private static RMIServer rmiServer;

	/**
	 * SocketServer.
	 */
	private static SocketServer socketServer;

	/**
	 * List of active game rooms.
	 */
	private static List<GameRoom> roomList;

	/**
	 * Private default constructor.
	 */
	private Server() {
		roomList = new ArrayList();
		System.out.println("Starting RMI Server..."); //TODO println
		rmiServer = new RMIServer();
		System.out.println("RMI Server started. Ready to accept connections."); //TODO println
		System.out.println("Starting Socket Server..."); //TODO println
		socketServer = new SocketServer();
		System.out.println("Socket Server started. Ready to accept connections."); //TODO println
		//socketServer = new SocketServer();
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
		System.out.println(" => Server :: Player " + uid + " connected"); //TODO println
		//Look for a game room where a client has already logged in.
		GameRoom room = getGameRoom(uid);
		if(room == null) {
			//Look for a game room that has not started yet.
			room = getNotStartedGameRoom();
			if(room == null) {
				//Create a new game room.
				System.out.println(" => Server :: Game room created."); //TODO println
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
