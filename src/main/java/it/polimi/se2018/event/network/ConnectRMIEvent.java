package it.polimi.se2018.event.network;

/**
 * @author Davide Yi Xian Hu
 */
public class ConnectRMIEvent extends ConnectEvent{
    /**
     * Constructor
     *
     * @param address the server address.
     * @param port    the port number of the server.
     */
    public ConnectRMIEvent(String address, int port) {
        super(address, port);
    }
}
