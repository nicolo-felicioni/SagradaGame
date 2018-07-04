package it.polimi.se2018.network.socket;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.network.ServerConfiguration;
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
	 * Server socket
	 */
	private ServerSocket serverSocket;

	/**
	 * Default constructor.
	 */
	public SocketServer(){
		try {
			serverSocket = new ServerSocket(ServerConfiguration.SOCKET_SERVER_PORT);
			new Thread(new Listener()).start();
		} catch(IOException ex) {
			try {
				serverSocket.close();
			} catch (IOException e) {
			}
		}
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
