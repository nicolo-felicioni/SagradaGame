package it.polimi.se2018.network.socket;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.SessionInterface;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author davide yi xian hu
 */
public class SocketServer {

	private final int port = 66666;

	/**
	 * Default constructor.
	 */
	public SocketServer(){
		try {
			ServerSocket serverSocket = new ServerSocket(port);
		} catch(IOException ex) {
		}
	}

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 * @param client the client.
	 * @return the session between the client and the server.
	 */
	public SessionInterface login(String uid, ClientInterface client) throws LoginException {
		SessionInterface session =new SocketServerSession();
		Server.getInstance().login(uid, session);
		return session;
	}

}
