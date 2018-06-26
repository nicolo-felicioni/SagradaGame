package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

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

    /**
     * Return the position of the window pattern as a value.
     * @return the position of the window pattern as a value.
     */
    public static WindowPatternPosition fromInt(int n){
        Optional<WindowPatternPosition> returned = Arrays.asList(WindowPatternPosition.values()).stream().filter(o -> o.toInt() == n).findAny();
        return returned.orElse(WindowPatternPosition.CHOSEN);
    }
}
