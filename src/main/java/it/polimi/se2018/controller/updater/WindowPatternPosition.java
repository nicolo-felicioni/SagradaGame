package it.polimi.se2018.controller.updater;

import java.io.Serializable;

public enum WindowPatternPosition implements Serializable {
    FIRST(0),
    SECOND(1),
    THIRD(2),
    FOURTH(3),
    CHOSEN(4);

    /**
     * The window pattern position as a value.
     */
    private int value;

    /**
     * Constructor.
     * @param i the value of the position.
     */
    WindowPatternPosition(int i) {
        this.value = i;
    }

    /**
     * Return the position of the window pattern as a value.
     * @return the position of the window pattern as a value.
     */
    public int toInt(){
        return this.value;
    }


}
