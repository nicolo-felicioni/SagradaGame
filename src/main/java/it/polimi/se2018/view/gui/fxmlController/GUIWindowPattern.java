package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Space;
import it.polimi.se2018.model.WindowPattern;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
     * Constructor.
     */
    public GUIWindowPattern() {
        this.guiSpaces = new ArrayList<>();
        this.getStyleClass().add("window-pattern");
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                guiSpaces.forEach(space -> {
                    if(space == event.getTarget()) {
                        space.setSelected(!space.isSelected());
                        selectedSpace = space.getSpace();
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
        System.out.println(window.getName());
        this.windowPattern = window.cloneWindowPattern();
        Space[][] spaces = windowPattern.getAllSpaces();
        for(int i = 0; i < spaces.length; i++) {
            for(int j = 0; j < spaces[i].length; j++) {
                GUISpace guiSpace = new GUISpace(spaces[i][j]);
                guiSpaces.add(guiSpace);
                this.add(guiSpace, j, i, 1, 1);
                GridPane.setHgrow(guiSpace, Priority.ALWAYS);
                GridPane.setVgrow(guiSpace, Priority.ALWAYS);
            }
        }
    }

    /**
     * Return the selected space.
     * @return the selected space.
     */
    public Space getSelectedSpace() {
        return selectedSpace.cloneSpace();
    }
}
