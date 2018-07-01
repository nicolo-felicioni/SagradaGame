package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.model.Space;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.cli.Printer;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import java.util.ArrayList;
import java.util.List;

public class GUIWindowPattern extends GridPane{

    /**
     * The draft pool.
     */
    private WindowPattern windowPattern;

    /**
     * GUIDie components
     */
    private List<GUISpace> guiSpaces;

    /**
     * The selected die;
     */
    private Space selectedSpace;

    /**
     * The selected position;
     */
    private Point selectedPosition;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * Name label
     */
    private Label label;

    /**
     * Constructor.
     */

    public GUIWindowPattern() {
        this.guiSpaces = new ArrayList<>();
        this.selected = false;
        this.getStyleClass().add("window-pattern");
        this.getRowConstraints().clear();
        this.getColumnConstraints().clear();
        RowConstraints header = new RowConstraints();
        header.setPrefHeight(65);
        header.setVgrow(Priority.NEVER);
        this.getRowConstraints().add(header);
        for(int i = 0; i < 4; i++) {
            RowConstraints r = new RowConstraints();
            r.setMinHeight(32);
            r.setPrefHeight(32);
            r.setVgrow(Priority.ALWAYS);
            this.getRowConstraints().add(r);
        }
        for(int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100/5);
            this.getColumnConstraints().add(column);
        }
        RowConstraints footer = new RowConstraints();
        footer.setPrefHeight(65);
        footer.setVgrow(Priority.NEVER);
        this.getRowConstraints().add(footer);
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                guiSpaces.forEach(space -> {
                    if(space == event.getTarget()) {
                        space.setSelected(!space.isSelected());
                        if(space.isSelected()) {
                            selectedSpace = space.getSpace();
                            selectedPosition = space.getPosition();
                        }
                        else {
                            selectedSpace = null;
                            selectedPosition = null;
                        }
                    } else {
                        space.setSelected(false);
                    }
                });
            }
        });
    }

    /**
     * Set the window pattern.
     * @param window the window pattern.
     */
    public void setWindowPattern(WindowPattern window) {
        this.getChildren().clear();
        this.windowPattern = window.cloneWindowPattern();
        Space[][] spaces = windowPattern.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    guiSpaces.add(guiSpace);
                    this.add(guiSpace, j, i + 1, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        label = new Label();
        label.setText(windowPattern.getName().toUpperCase());
        GridPane.setHalignment(label, HPos.CENTER);
        label.getStyleClass().add("window-pattern-name");
        this.add(label, 0, 0, 5, 1);
    }

    public void highlightPlaceableSpaces(Die die) {
        this.getChildren().clear();
        Space[][] spaces = windowPattern.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    if(die != null && windowPattern.isPlaceable(die, new Point(i, j)))
                        guiSpace.getStyleClass().add("space-highlighted");
                    guiSpaces.add(guiSpace);
                    this.add(guiSpace, j, i + 1, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        label = new Label();
        label.setText(windowPattern.getName().toUpperCase());
        GridPane.setHalignment(label, HPos.CENTER);
        label.getStyleClass().add("window-pattern-name");
        this.add(label, 0, 0, 5, 1);
    }

    /**
     * Return the window pattern.
     * @return the window pattern.
     */
    public WindowPattern getWindowPattern() {
        return windowPattern;
    }

    /**
     * Return the selected space.
     * @return the selected space.
     */
    public Space getSelectedSpace() {
        return selectedSpace.cloneSpace();
    }

    /**
     * Return the selected position.
     * @return the selected position.
     */
    public Point getSelectedPosition() {
        return selectedPosition.clonePoint();
    }

    /**
     * Return true if the window pattern is selected.
     * @return true if the die is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set if the window pattern is selected.
     * @param selected if the window pattern is selected.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        refresh();
    }

    /**
     * Refresh grafically.
     */
    public void refresh() {
        this.getStyleClass().remove(0, this.getStyleClass().size());
        if(selected) {
            this.getStyleClass().add("window-pattern-selected");
        }else{
            this.getStyleClass().add("window-pattern");
        }
    }

    /**
     * Return true if the control has been correctly initialized.
     * @return true if the control has been correctly initialized.
     */
    public boolean isInitialized() {
        return windowPattern != null;
    }

}
