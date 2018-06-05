package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.client.AbstractClient;
import it.polimi.se2018.network.server.ServerInterface;
import it.polimi.se2018.network.utils.NetworkCommandObservable;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class RMIClient extends AbstractClient implements Remote, NetworkCommandObservable {


	/**
	 * Server session. It handle the requests to the server.
	 */
	private List<NetworkCommandObserver> observers;

	/**
	 * RMI server.
	 */
	private ServerInterface server;

	/**
	 * Connect to the RMI server.
	 *
	 * @param address the ip address of the RMI server.
	 * @param port the port number of the RMI server.
	 *
	 * @throws RemoteException if the connection with thr RMI server fails.
	 * @throws NotBoundException if the client can not connect to the RMI server.
	 */
	public void connect(String address, int port) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(address, port);
		server = (ServerInterface) registry.lookup("RMIServer");
		UnicastRemoteObject.exportObject(this, 0);
	}

	/**
	 * Connect to the RMI server.
	 *
	 * @param uid the unique identifier of the user.
	 *
	 * @throws LoginException if the connection with thr RMI server fails.
	 */
	@Override
	public void login(String uid) throws LoginException {
		try {
			this.addCommandObserver(server.login(uid, this));
		}catch(RemoteException | NetworkException e) {
			throw new LoginException("Login to server failed.");
		}
	}

	/**
	 * {@inheritDoc}
	 * Add a server session.
	 */
	@Override
	public void addCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.observers.add(observer);
	}

	/**
	 * {@inheritDoc}
	 * Add a server session.
	 */
	@Override
	public void removeCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.observers.remove(observer);
	}

	/**
	 * {@inheritDoc}
	 * Notify a command to the server session.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException {
		for(NetworkCommandObserver obs : observers) {
			obs.handle(command);
		}
	}

	/**
	 * {@inheritDoc}
	 * Forward the command to the server session.
	 */
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		this.notify(command);
	}

}