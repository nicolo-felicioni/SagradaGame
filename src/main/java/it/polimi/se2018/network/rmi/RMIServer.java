package it.polimi.se2018.network.rmi;

import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.network.server.ServerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @author davide yi xian hu
 */
public class RMIServer implements Remote, ServerInterface {

	private static RMIServer instance = null;
	private static Registry registry;
	private final int RMI_SERVER_PORT = 33333;

	private RMIServer(){
		registry = null;
		try {
			registry = LocateRegistry.createRegistry(RMI_SERVER_PORT);
		} catch (RemoteException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
			try {
				registry = LocateRegistry.getRegistry(RMI_SERVER_PORT);
			} catch (RemoteException ex) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		}
		if (registry != null) {
			try {
				registry.rebind("RMIServer", this);
				UnicastRemoteObject.exportObject(this, RMI_SERVER_PORT);
			} catch (RemoteException e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		}
	}

	public static RMIServer getInstance() {
		if (instance == null) {
			instance = new RMIServer();
		}
		return instance;
	}

	public static RMISession login(String uid, RMIClient client) throws SessionException {
		return new RMISession(client, RMIServer.getInstance());
	}
}
