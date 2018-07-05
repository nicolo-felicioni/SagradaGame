package it.polimi.se2018.app;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.gui.GUIController;

public class ClientApp {

    public static void main(String[] args) {

        ClientConfiguration.getClientConfiguration();
        if (ClientConfiguration.getClient().equals("cli"))
            (new CommandLineInterface()).start();
        else if (ClientConfiguration.getClient().equals("gui"))
            new GUIController().run();


    }
}
