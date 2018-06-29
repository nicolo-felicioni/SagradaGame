package it.polimi.se2018.network.rmi;

import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.ServerConfiguration;
import it.polimi.se2018.network.server.GameRoom;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.server.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Davide Yi Xian Hu
 */
public class RMIServer implements RMIServerInterface {

	/**
	 * Default constructor.
	 */
	public RMIServer(){
		Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(ServerConfiguration.RMI_SERVER_PORT);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (registry != null) {
			try {
				registry.rebind("RMIServer", this);
				UnicastRemoteObject.exportObject(this, ServerConfiguration.RMI_SERVER_PORT);
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
	public synchronized RMIServerSessionInterface login(String uid, RMIClientInterface client) throws RemoteException, NetworkException {
		RMIServerSession session = new RMIServerSession(uid);
		session.addClientObserver(client);
		Server.getInstance().login(uid, session);
		UnicastRemoteObject.exportObject(session, ServerConfiguration.RMI_SERVER_PORT);
		return session;
	}
}
