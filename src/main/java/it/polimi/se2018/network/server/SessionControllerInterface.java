package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.utils.NetworkCommandForwarder;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface SessionControllerInterface extends NetworkCommandForwarder {

	String getUID();

}
