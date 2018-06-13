package it.polimi.se2018.controller.updater;

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
}
