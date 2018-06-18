package it.polimi.se2018.event.network;

public abstract class ConnectEvent {

    /**
     * The server address;
     */
    private String address;

    /**
     * The port number of the server.
     */
    private int port;

    /**
     * Constructor
     * @param address the server address.
     * @param port the port number of the server.
     */
    public ConnectEvent(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Server address getter.
     * @return the server address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Port number getter.
     * @return the port number of the server.
     */
    public int getPort() {
        return port;
    }
}
