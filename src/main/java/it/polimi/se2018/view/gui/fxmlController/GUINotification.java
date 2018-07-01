package it.polimi.se2018.view.gui.fxmlController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GUINotification {
    @FXML
    Label text;

    public GUINotification(String string){
        text.setText(string);
    }
    public void setText(String string){
        text.setText(string);
    }
}
