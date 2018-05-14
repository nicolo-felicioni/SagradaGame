package it.polimi.se2018.model;

/**
 * @author PeiQing Gao
 */
public enum DieValue {
	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX;

	public static DieValue getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}


}