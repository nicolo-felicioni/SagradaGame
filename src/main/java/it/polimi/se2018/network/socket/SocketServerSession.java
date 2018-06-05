package it.polimi.se2018.network.socket;

import it.polimi.se2018.event.Event;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class SocketServerSession implements SessionInterface {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	private NetworkListener listener;

	/**
	 * Server session controller.
	 */
	private List<NetworkCommandObserver> observers;

	public SocketServerSession(Socket socket) {
		this.socket = socket;
		try {
			this.inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			this.outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}

	/**
	 * Initialize the connection.
	 * It has 2 steps. It wait for a login message, then it log the client to the Server.
	 */
	public void init() {
		try {
			String login = this.inStream.readUTF();
			Server.getInstance().login(new LoginMessage(login, true).getUid(), this);
		} catch (IOException | LoginException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void logout(String uid) throws SessionException {

	}

	@Override
	public String getUID() {
		return null;
	}

	/**
	 * Add a network command observer.
	 *
	 * @param observer the network command observer.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void addCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.observers.add(observer);
	}

	/**
	 * Notify a network command.
	 *
	 * @param command the network command to be executed.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void notify(Event command) throws RemoteException, NetworkException {
		for(NetworkCommandObserver obs : observers) {
			obs.handle(command);
		}
	}

	/**
	 * Remove a network command observer.
	 *
	 * @param observer the network command observer.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void removeCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.observers.remove(observer);
	}

	/**
	 * Handle a command from the network.
	 *
	 * @param command the command to be executed.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void handle(Event command) throws RemoteException, NetworkException {
		this.notify(command);
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
		String message = updater.toString();
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
