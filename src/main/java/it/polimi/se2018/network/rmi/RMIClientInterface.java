package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.LoginException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {

    /**
     * Login a client to the server.
     *
     * @param uid the unique identifier of the client.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void login (String uid) throws RemoteException, LoginException;

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

}
