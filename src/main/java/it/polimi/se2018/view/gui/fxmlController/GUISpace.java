package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Space;
import javafx.scene.layout.Pane;

public class GUISpace extends Pane {

    /**
     * The space.
     */
    private Space space;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * Constructor.
     * @param space the space.
     */
    public GUISpace(Space space) {
        this.space = space.cloneSpace();
        this.refresh();
    }

    /**
     * Set the space.
     * @param space the space.
     */
    public void setSpace(Space space) {
        this.space = space.cloneSpace();
        this.refresh();
    }

    /**
     * Return the space.
     * @return the space.
     */
    public Space getSpace() {
        return space.cloneSpace();
    }

    /**
     * Return true if the space is selected.
     * @return true if the space is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set if the space is selected.
     * @param selected if the space is selected.
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
            this.getStyleClass().add("space-selected");
        }else{
            this.getStyleClass().add("space");
        }
        if(space.isColorRestricted()) {
            this.getStyleClass().add("space-" + space.getColorRestriction().toString().toLowerCase());
        }else if(space.isValueRestricted()) {
            this.getStyleClass().add("space-" + space.getValueRestriction().toString().toLowerCase());
        }else {
            this.getStyleClass().add("space-blank");
        }
        if(space.hasDie()) {
            this.getChildren().removeAll();
            this.getChildren().add(new GUIDie(space.getDie()));
        }

    }

}