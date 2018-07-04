package it.polimi.se2018.app;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.gui.fxmlController.GUIController;
import javafx.application.Platform;

public class ClientApp {

    public static void main(String[] args) {

        ClientConfiguration.getClientConfiguration();
        if (ClientConfiguration.CLIENT.equals("cli"))
            (new CommandLineInterface()).start();
        else if (ClientConfiguration.CLIENT.equals("gui"))
            new GUIController().run();


    }
}
