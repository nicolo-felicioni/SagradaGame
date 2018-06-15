package it.polimi.se2018.view.gui;

import it.polimi.se2018.observer.network.ConnectRMIObserver;
import it.polimi.se2018.observer.network.ConnectSocketObserver;
import it.polimi.se2018.observer.network.LoginObserver;
import it.polimi.se2018.view.gui.fxmlController.GUIController;
import it.polimi.se2018.view.gui.fxmlController.LoginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import sun.applet.Main;

import java.awt.*;
import java.io.IOException;

public class MainGUI extends Application{
    private static GUIController guiController;

    public MainGUI(){
        super();
    }

    public MainGUI(GUIController guiController){
        super();
        this.guiController=guiController;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
        Parent root= (Parent) loader.load();
        LoginController controller = loader.getController();
        System.out.println(MainGUI.guiController);
        System.out.println((LoginController)loader.getController());
        controller.addGameObserver(guiController);
        controller.addObserver((LoginObserver) MainGUI.guiController);
        controller.addObserver((ConnectRMIObserver) MainGUI.guiController);
        controller.addObserver((ConnectSocketObserver) MainGUI.guiController);
        primaryStage.setTitle("Sagrada-The Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void run() {
        Application.launch(new String[0]);
    }

}
