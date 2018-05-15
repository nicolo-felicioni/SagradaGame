package it.polimi.se2018.model;

/**
 *@author PeiQing Gao
 */
public enum DieColor {
	RED(0),
	BLUE(1),
	YELLOW(2),
	GREEN(3),
	PURPLE(4);

	private int value;

    DieColor(int i) {
        this.value = i;
    }

    public int toInt(){
	    return this.value;
    }
    public static DieColor fromInt(int n) {
        for (DieColor color : DieColor.values()) {
            if (color.value == n) {
                return color;
            }
        }
         return null;
    }


	public static DieColor getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}
}