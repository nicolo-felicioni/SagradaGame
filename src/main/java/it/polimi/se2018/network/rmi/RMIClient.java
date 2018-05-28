package it.polimi.se2018.network.rmi;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.client.AbstractClient;
import it.polimi.se2018.network.client.ClientSessionController;
import it.polimi.se2018.network.server.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author davide yi xian hu
 */
public class RMIClient extends AbstractClient implements Remote{

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
	 * @throws RemoteException if the connection with thr RMI server fails.
	 */
	@Override
	public void login(String uid) throws LoginException {
		try {
			this.addCommandObserverver(server.login(uid, this));
		}catch(RemoteException | NetworkException e) {
			throw new LoginException("Login to server failed.");
		}
	}

}