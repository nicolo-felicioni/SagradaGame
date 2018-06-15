package it.polimi.se2018.event.network;

public class ConnectSocketEvent extends ConnectEvent {
    /**
     * Constructor
     *
     * @param address the server address.
     * @param port    the port number of the server.
     */
    public ConnectSocketEvent(String address, int port) {
        super(address, port);
    }
}
