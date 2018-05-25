package it.polimi.se2018.network.rmi;

import it.polimi.se2018.model.*;
import it.polimi.se2018.network.SessionControllerInterface;
import it.polimi.se2018.network.client.AbstractClient;
import it.polimi.se2018.network.client.ClientInterface;
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

	private ServerInterface server;

	/**
	 * Constructor of class.
	 */
	public RMIClient() {
		super(new ClientSessionController());
	}

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
	 * Update the dice bag.
	 * @param diceBag the updated dice bag.
	 */
	@Override
	public void updateDiceBag(DiceBag diceBag) throws RemoteException{
		super.updateDiceBag(diceBag);
	}
}