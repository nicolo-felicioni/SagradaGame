package it.polimi.se2018.network.rmi;

import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.SessionInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Davide Yi Xian Hu
 */
public interface RMIServerInterface extends Remote {

    /**
     * Login a client to the server.
     *
     * @param uid the unique identifier of the client.
     * @param client the client.
     * @return the session between the client and the server.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    RMIServerSessionInterface login(String uid, RMIClientInterface client) throws RemoteException, NetworkException;

}
