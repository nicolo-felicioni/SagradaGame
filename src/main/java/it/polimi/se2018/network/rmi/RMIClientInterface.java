package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Davide Yi Xian Hu
 */
public interface RMIClientInterface extends Remote {

    /**
     * Login a client to the server.
     *
     * @param uid the unique identifier of the client.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void login (String uid) throws RemoteException, LoginException;

    /**
     * Reconnect a client to the server.
     *
     * @param uid the unique identifier of the client.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void reconnect (String uid) throws RemoteException, LoginException;

    /**
     * Getter of the unique identifier.
     *
     * @return the unique identifier.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    String getUid () throws RemoteException;

    /**
     * Handle a view update from the network.
     * @param updater the view updater.
     *
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(ViewUpdaterInterface updater) throws RemoteException;

    /**
     * Disconnect the client.
     *
     * @throws NetworkException if the client can not connect to the server.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void disconnect() throws NetworkException, RemoteException;
}
