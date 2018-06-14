package it.polimi.se2018.controller.updater;

import java.util.Arrays;
import java.util.Optional;

public enum CardPosition {
    LEFT(0),
    CENTER(1),
    RIGHT(2);

    /**
     * The card position as a value.
     */
    private int value;

    /**
     * Constructor.
     * @param i the value of the card position.
     */
    CardPosition(int i) {
        this.value = i;
    }

    /**
     * Return the card position as a value.
     * @return the card position as a value.
     */
    public int toInt(){
        return this.value;
    }

    public static CardPosition fromInt(int n){
        Optional<CardPosition> returned = Arrays.asList(CardPosition.values()).stream().filter(o -> o.toInt() == n).findAny();
        return returned.get();
    }
}
