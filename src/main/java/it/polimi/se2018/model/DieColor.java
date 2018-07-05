package it.polimi.se2018.model;

import org.fusesource.jansi.Ansi;

import java.io.Serializable;
import java.util.Random;

/**
 *@author PeiQing Gao
 */
public enum DieColor implements Serializable {
	RED(0),
	BLUE(1),
	YELLOW(2),
	GREEN(3),
	PURPLE(4);

	private int value;

    /**
     * Constructor
     * @param i
     */
    DieColor(int i) {
        this.value = i;
    }

    /**
     *
     * @return the corresponding value of the color
     */
    public int toInt(){
	    return this.value;
    }

    public Ansi.Color toAnsiColor(){


        if(this == PURPLE)  //can't invoke valueOf for Purple
            return Ansi.Color.MAGENTA;

        return Ansi.Color.valueOf(this.name());

    }

    /**
     *
     * @param n an integer number
     * @return the corresponding color of the input n
     */
    public static DieColor fromInt(int n) {
        for (DieColor color : DieColor.values()) {
            if (color.value == n) {
                return color;
            }
        }
         return null;
    }

    /**
     * @return a random color
     */
	public static DieColor getRandom() {
		return values()[(new Random().nextInt(values().length))];
	}
}