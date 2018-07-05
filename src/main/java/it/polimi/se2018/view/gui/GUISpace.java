package it.polimi.se2018.view.gui;

import it.polimi.se2018.model.Point;
import it.polimi.se2018.model.Space;
import javafx.scene.layout.Pane;

public class GUISpace extends Pane {

    /**
     * The space.
     */
    private Space space;

    /**
     * The space.
     */
    private Point position;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * Constructor.
     * @param space the space.
     */
    public GUISpace(Space space, Point p) {
        this.space = space.cloneSpace();
        this.position = p;
        this.refresh();
    }

    /**
     * Set the space.
     * @param space the space.
     * @param p the position of the space.
     */
    public void setSpace(Space space, Point p) {
        this.space = space.cloneSpace();
        this.position = p;
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
     * Return the position of the space.
     * @return the position of the space.
     */
    public Point getPosition() {
        return position.clonePoint();
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
        if(space.hasDie()) {
            //this.getChildren().removeAll();
            this.getStyleClass().add("fill-90");
            this.getStyleClass().add(this.space.getDie().getColor().toString().toLowerCase() + "-" + this.space.getDie().getValue().toString().toLowerCase());
        }else if(space.isColorRestricted()) {
            this.getStyleClass().add("fill-80");
            this.getStyleClass().add("space-" + space.getColorRestriction().toString().toLowerCase());
        }else if(space.isValueRestricted()) {
            this.getStyleClass().add("fill-80");
            this.getStyleClass().add("space-" + space.getValueRestriction().toString().toLowerCase());
        }else {
            this.getStyleClass().add("fill-80");
            this.getStyleClass().add("space-blank");
        }
    }

}
