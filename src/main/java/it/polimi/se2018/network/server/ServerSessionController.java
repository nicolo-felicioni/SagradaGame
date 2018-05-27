package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class ServerSessionController implements SessionControllerInterface {

	private SessionInterface session;
	private NetworkCommandObserver gameRoom;
	private String uid;

	public ServerSessionController(SessionInterface session) {
		this.session = session;
		this.uid = session.getUID();
		this.gameRoom = null;
	}


	@Override
	public String getUID() {
		return uid;
	}

	/**
	 * {@inheritDoc}
	 * Add a game room.
	 */
	@Override
	public void addCommandObserverver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		gameRoom = observer;
	}

	/**
	 * {@inheritDoc}
	 * Notify the game room.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException {
		this.gameRoom.handle(command);
	}

	/**
	 * {@inheritDoc}
	 * Forward the command to the game room.
	 */
	@Override
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		this.notify(command);
	}
}
