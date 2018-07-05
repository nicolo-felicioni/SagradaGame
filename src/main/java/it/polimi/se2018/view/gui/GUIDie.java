package it.polimi.se2018.view.gui;

import it.polimi.se2018.model.Die;
import javafx.scene.layout.Pane;

public class GUIDie extends Pane {

    /**
     * The die.
     */
    private Die die;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * Constructor.
     * @param die the die.
     */
    public GUIDie(Die die) {
        this.die = die.cloneDie();
        this.refresh();
    }

    /**
     * Set the die.
     * @param die the die.
     */
    public void setDie(Die die) {
        this.die = die.cloneDie();
        this.refresh();
    }

    /**
     * Return the die.
     * @return the die.
     */
    public Die getDie() {
        return die.cloneDie();
    }

    /**
     * Return true if the die is selected.
     * @return true if the die is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set if the die is selected.
     * @param selected if the die is selected.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        this.refresh();
    }

    /**
     * Refresh grafically.
     */
    private void refresh() {

        this.getStyleClass().clear();
        if(selected) {
            this.getStyleClass().add("die-selected");
        }else{
            this.getStyleClass().add("die");
        }
        this.getStyleClass().add(this.die.getColor().toString().toLowerCase() + "-" + this.die.getValue().toString().toLowerCase());
    }

}
