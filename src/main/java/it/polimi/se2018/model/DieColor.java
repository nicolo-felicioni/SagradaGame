package it.polimi.se2018.model;

/**
 *@author PeiQing Gao
 */
public enum DieColor {
	RED,
	BLUE,
	YELLOW,
	GREEN,
	PURPLE;


	public static DieColor getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}
}