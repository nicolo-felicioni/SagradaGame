package it.polimi.se2018.network.socket;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class SocketServerSession implements SessionInterface {

	private Socket socket;
	private DataInputStream inStream;
	private BufferedOutputStream outStream;
	private NetworkListener listener;

	/**
	 * Server session controller.
	 */
	private NetworkCommandObserver controller;

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
		this.controller = observer;
	}

	/**
	 * Notify a network command.
	 *
	 * @param command the network command to be executed.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException {
		this.controller.handle(command);
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
