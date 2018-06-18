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

	/**
	 * Socket server port number.
	 */
	private final int port = 55555;

	/**
	 * Server socket
	 */
	private ServerSocket serverSocket;

	/**
	 * Default constructor.
	 */
	public SocketServer(){
		try {
			serverSocket = new ServerSocket(port);
			new Thread(new Listener()).start();
		} catch(IOException ex) {
			try {
				serverSocket.close();
			} catch (IOException e) {
			}
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
		return null;
	}

	private class Listener implements Runnable {
		private boolean run = true;

		@Override
		public void run() {
			while (this.run) {
				try {
					new SocketServerSession(serverSocket.accept());
				} catch (IOException e) {
				}
			}
		}
	}

}
