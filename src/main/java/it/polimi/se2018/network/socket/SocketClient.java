package it.polimi.se2018.network.socket;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.client.AbstractClient;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class SocketClient extends AbstractClient {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	private NetworkListener listener;

	/**
	 * Connect to the socket server.
	 *
	 * @param address the ip address of the socket server.
	 * @param port the port number of the socket server.
	 *
	 */
	public void connect(String address, int port) {
		try {
			this.socket = new Socket(address, port);
			this.login(getUid());
			this.inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			this.outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			this.listener = new NetworkListener();
			new Thread(listener).start();
		} catch (IOException|LoginException e) {
		}
	}

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	@Override
	public void login(String uid) throws LoginException {
		try {
			this.send(new LoginMessage(this.getUid()).toJsonString());
		}catch (IOException e){
			new LoginException("Login failed");
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
		String message = command.toString();

	}

	/**
	 * Send a message to the server.
	 *
	 * @param text the message to send to the server session.
	 */
	private void send(String text) throws IOException{
		this.outStream.writeUTF(text);
		this.outStream.flush();
	}

	private class NetworkListener implements Runnable {
		private boolean run = true;

		@Override
		public void run() {
			while(this.run) {
				try {
					String request = inStream.readUTF();
				} catch (IOException e) {
				}
			}
		}
	}
}
