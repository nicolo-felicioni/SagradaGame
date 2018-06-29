package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Die;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GUIDie extends Pane {

    /**
     * The die.
     */
    private Die die;

    /**
     * Set the die.
     * @param die the die.
     */
    public void setDie(Die die) {
        this.die = die.cloneDie();
        String styleClass = this.die.getColor() + "-" + this.die.getValue();
        this.getStyleClass().add(styleClass);
    }

    /**
     * Return the die.
     * @return the die.
     */
    public Die getDie() {
        return die.cloneDie();
    }

}
