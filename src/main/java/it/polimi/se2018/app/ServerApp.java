package it.polimi.se2018.app;

import it.polimi.se2018.network.ServerConfiguration;
import it.polimi.se2018.network.server.Server;

public class ServerApp {

    public static void main(String[] args) {
        ServerConfiguration.getServerConfiguration();
        System.out.println(ServerConfiguration.RMI_SERVER_PORT);
        System.out.println(ServerConfiguration.SOCKET_SERVER_PORT);
        Server.getInstance();
    }
}
