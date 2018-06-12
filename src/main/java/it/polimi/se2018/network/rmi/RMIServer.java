package it.polimi.se2018.network.rmi;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.ServerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author davide yi xian hu
 */
public class RMIServer implements Remote, ServerInterface {

	/**
	 * Port number of the RMI Server.
	 */
	private final int RMI_SERVER_PORT = 33333;

	/**
	 * Default constructor.
	 */
	public RMIServer(){
		Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(RMI_SERVER_PORT);
		} catch (RemoteException e) {
			e.printStackTrace();
			try {
				registry = LocateRegistry.getRegistry(RMI_SERVER_PORT);
			} catch (RemoteException ex) {
				e.printStackTrace();
			}
		}
		if (registry != null) {
			try {
				registry.rebind("RMIServer", this);
				UnicastRemoteObject.exportObject(this, RMI_SERVER_PORT);
			} catch (RemoteException e) {
				e.printStackTrace();
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
	public SessionInterface login(String uid, ClientInterface client) throws RemoteException, NetworkException {
		SessionInterface session = new RMIServerSession();
		session.addViewUpdaterObserver(client);
		client.addGameObserver(session);
		Server.getInstance().login(uid, session);
		return session;
	}
}
