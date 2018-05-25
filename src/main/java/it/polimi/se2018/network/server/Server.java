package it.polimi.se2018.network.server;

import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.GameRoom;
import it.polimi.se2018.network.rmi.RMIServer;
import it.polimi.se2018.network.socket.SocketServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class Server {

	private static RMIServer rmiServer;
	private static SocketServer socketServer;
	private static List<GameRoom> roomList;

	private Server() {
		roomList = new ArrayList();
		rmiServer = RMIServer.getInstance();
		socketServer = SocketServer.getInstance();
	}

	public static GameRoom login(String uid) throws NetworkException {
		return null;
	}

}
