package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIWindowPattern extends GridPane{

    /**
     * The draft pool.
     */
    private WindowPattern window;

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

    @FXML
    private GridPane windowPattern;

    @FXML
    private Label name;

    @FXML
    private GridPane difficulty;

    /**
     * Constructor.
     */

    public GUIWindowPattern() {
        this.guiSpaces = new ArrayList<>();
        this.selected = false;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIWindowPattern.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try  {
            loader.load();
            windowPattern.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    guiSpaces.forEach(space -> {
                        if (space == event.getTarget()) {
                            space.setSelected(!space.isSelected());
                            if (space.isSelected()) {
                                selectedSpace = space.getSpace();
                                selectedPosition = space.getPosition();
                            } else {
                                selectedSpace = null;
                                selectedPosition = null;
                            }
                        } else {
                            space.setSelected(false);
                        }
                    });
                }
            });
        } catch (IOException exception)  {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Set the window pattern.
     * @param window the window pattern.
     */
    public void setWindowPattern(WindowPattern window) {
        System.out.println(this);
        this.windowPattern.getChildren().clear();
        this.window = window.cloneWindowPattern();
        Space[][] spaces = this.window.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    guiSpaces.add(guiSpace);
                    this.windowPattern.add(guiSpace, j, i, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        this.name.setText(window.getName().toUpperCase());
    }

    public void highlightPlaceableSpaces(Die die) {
        this.windowPattern.getChildren().clear();
        Space[][] spaces = window.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    if(die != null && window.isPlaceable(die, new Point(i, j)))
                        guiSpace.getStyleClass().add("space-highlighted");
                    guiSpaces.add(guiSpace);
                    this.windowPattern.add(guiSpace, j, i, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        this.name.setText(window.getName().toUpperCase());
    }

    public void highlightPlaceableIgnoreColorSpaces(Die die) {
        this.windowPattern.getChildren().clear();
        Space[][] spaces = window.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    if(die != null && window.isPlaceableIgnoreColor(die, new Point(i, j)))
                        guiSpace.getStyleClass().add("space-highlighted");
                    guiSpaces.add(guiSpace);
                    this.windowPattern.add(guiSpace, j, i, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        this.name.setText(window.getName().toUpperCase());
    }

    public void highlightPlaceableIgnoreValueSpaces(Die die) {
        this.windowPattern.getChildren().clear();
        Space[][] spaces = window.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    if(die != null && window.isPlaceableIgnoreValue(die, new Point(i, j)))
                        guiSpace.getStyleClass().add("space-highlighted");
                    guiSpaces.add(guiSpace);
                    this.windowPattern.add(guiSpace, j, i, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        this.name.setText(window.getName().toUpperCase());
    }

    public void highlightDiceMatchColors(List<DieColor> colors) {
        this.windowPattern.getChildren().clear();
        Space[][] spaces = window.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    Space sp = spaces[i][j];
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    if(colors != null && sp.hasDie() && colors.contains(sp.getDie().getColor()))
                        guiSpace.getStyleClass().add("space-highlighted");
                    guiSpaces.add(guiSpace);
                    this.windowPattern.add(guiSpace, j, i, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        this.name.setText(window.getName().toUpperCase());
    }

    public void highlightPlaceableNoAdjacentSpaces(Die die) {
        this.windowPattern.getChildren().clear();
        Space[][] spaces = window.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                try {
                    GUISpace guiSpace = new GUISpace(spaces[i][j], new Point(i, j));
                    if(die != null && window.isPlaceableNoAdjacent(die, new Point(i, j)))
                        guiSpace.getStyleClass().add("space-highlighted");
                    guiSpaces.add(guiSpace);
                    this.windowPattern.add(guiSpace, j, i, 1, 1);
                    GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                    GridPane.setVgrow(guiSpace, Priority.ALWAYS);
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        this.name.setText(window.getName().toUpperCase());
    }

    /**
     * Return the window pattern.
     * @return the window pattern.
     */
    public WindowPattern getWindowPattern() {
        return window.cloneWindowPattern();
    }

    /**
     * Return the selected space.
     * @return the selected space.
     */
    public Space getSelectedSpace() {
        if(selectedSpace != null)
            return selectedSpace.cloneSpace();
        else
            return null;
    }

    /**
     * Return the selected position.
     * @return the selected position.
     */
    public Point getSelectedPosition() {
        if(selectedPosition != null)
            return selectedPosition.clonePoint();
        else
            return null;
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
        this.windowPattern.getStyleClass().remove(0, this.windowPattern.getStyleClass().size());
        if(selected) {
            this.windowPattern.getStyleClass().add("window-pattern-selected");
        }else{
            this.windowPattern.getStyleClass().add("window-pattern");
        }
    }

    /**
     * Return true if the control has been correctly initialized.
     * @return true if the control has been correctly initialized.
     */

    public boolean isInitialized() {
        return window != null;
    }

}
