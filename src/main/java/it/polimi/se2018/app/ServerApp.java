package it.polimi.se2018.app;

import it.polimi.se2018.network.ServerConfiguration;
import it.polimi.se2018.network.server.Server;

public class ServerApp {

    public static void main(String[] args) {
        ServerConfiguration.getServerConfiguration();
        Server.getInstance();
    }
}
