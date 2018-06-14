package it.polimi.se2018.network.rmi;

import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.GameRoom;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.server.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author davide yi xian hu
 */
public class RMIServer implements RMIServerInterface {

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
	public RMIServerSessionInterface login(String uid, RMIClientInterface client) throws RemoteException, NetworkException {
		RMIServerSession session = new RMIServerSession(uid);
		session.addClientObserver(client);
		Server.getInstance().login(uid, session);
		UnicastRemoteObject.exportObject(session, RMI_SERVER_PORT);
		return session;
	}
}
