package it.polimi.se2018.view.gui;

import it.polimi.se2018.model.PrivateObjectiveCard;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIPrivateObjectiveCard extends GridPane {

    /**
     * The private objective card.
     */
    private PrivateObjectiveCard privateObjectiveCard;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * True if the tool card shows more options.
     */
    private boolean advancedMode;

    @FXML
    GridPane foreground;

    /**
     * Constructor.
     */
    public GUIPrivateObjectiveCard( ) {
        this.privateObjectiveCard = null;
        this.selected = false;
        this.advancedMode = true;
        this.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIPrivateCard.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try  {
            loader.load();
            this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(advancedMode)
                        foreground.setVisible(true);
                }
            });
            this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(advancedMode)
                        foreground.setVisible(false);
                }
            });
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Platform.runLater(
                            () -> {
                                if(advancedMode) {
                                    GUIPrivateObjectiveCard gui = new GUIPrivateObjectiveCard();
                                    gui.setPrivateObjectiveCard(privateObjectiveCard);
                                    gui.setAdvancedMode(false);
                                    Scene scene = new Scene(gui, 300, 420);
                                    scene.getStylesheets().add("css/style.css");
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.setTitle(privateObjectiveCard.getName());
                                    stage.setResizable(false);
                                    stage.showAndWait();
                                }
                            }
                    );
                }
            });
            foreground.setVisible(false);
        } catch (IOException exception)  {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Set the private objective card.
     *
     * @param privateObjectiveCard the private objective card.
     */
    public void setPrivateObjectiveCard(PrivateObjectiveCard privateObjectiveCard) {
        this.privateObjectiveCard = privateObjectiveCard;
        this.setVisible(true);
        this.refresh();
    }

    /**
     * Return the private objective card.
     *
     * @return the private objective card.
     */
    public PrivateObjectiveCard getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }

    /**
     * Return true if the private objective card is selected.
     *
     * @return true if the private objective card is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set if the private objective card is selected.
     *
     * @param selected if the private objective card is selected.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        this.refresh();
    }

    /**
     * Set the advanced mode.
     * @param advancedMode the advanced mode.
     */
    public void setAdvancedMode(boolean advancedMode) {
        this.advancedMode = advancedMode;
        if(!advancedMode) {
            foreground.setVisible(true);
        }
    }

    /**
     * Refresh grafically.
     */
    private void refresh() {
        this.getStyleClass().clear();
        if (selected) {
            foreground.getStyleClass().add("private-card-selected");
        } else {
            foreground.getStyleClass().add("private-card");
        }
        if(privateObjectiveCard != null)
            foreground.getStyleClass().add("private-card-" + privateObjectiveCard.getColor().toString().toLowerCase());
    }

    /**
     * Return true if the control has been correctly initialized.
     * @return true if the control has been correctly initialized.
     */
    public boolean isInitialized() {
        return privateObjectiveCard != null;
    }

}
