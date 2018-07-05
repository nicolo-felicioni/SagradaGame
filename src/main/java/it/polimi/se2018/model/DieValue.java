package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.Random;

/**
 * @author PeiQing Gao
 */
public enum DieValue implements Serializable {
	ONE(1, "\u2680"),
	TWO(2, "\u2681"),
	THREE(3, "\u2682"),
	FOUR(4, "\u2683"),
	FIVE(5, "\u2684"),
	SIX(6, "\u2685");

	/**
	 * The highest value.
	 */
	private static final DieValue MAX_VALUE = DieValue.SIX;

	/**
	 * The lowest value.
	 */
	private static final DieValue MIN_VALUE = DieValue.ONE;

	private int dieNumber;
	private String unicodeValue;


	DieValue(int dieValue, String unicodeValue){
		this.dieNumber =dieValue;
		this.unicodeValue=unicodeValue;
	}

	/**
	 * returns the value as an integer
	 * @return the value as an integer
	 */
	public int toInt(){
			return this.dieNumber;
	}

	public String toUnicode(){
		return this.unicodeValue;
	}

	/**
	 * returns the corresponding die Value of input n
	 * @param n an integer
	 * @return corresponding die Value of input n
	 */
	public static DieValue fromInt(int n){
		for (DieValue value : DieValue.values()) {
			if (value.dieNumber == n) {
				return value;
			}
		}
		return null;
	}

	/**
	 * get a random die value.
	 * @return a random die Value
	 */
	public static DieValue getRandom() {
		return values()[new Random().nextInt(values().length)];
	}

	/**
	 * Return the opposite value of this value.
	 * @return the opposite value of this value.
	 */
	public DieValue oppositeValue() {
		return DieValue.fromInt(DieValue.MAX_VALUE.toInt() + DieValue.MIN_VALUE.toInt() - this.toInt());
	}

	/**
	 * Return the increased value of this value.
	 * @return the increased value of this value.
	 */
	public DieValue increaseValue() {
		if(this == DieValue.MAX_VALUE) {
			return this;
		} else {
			return DieValue.fromInt(this.toInt() + 1);
		}
	}

	/**
	 * Return the decreased value of this value.
	 * @return the decreased value of this value.
	 */
	public DieValue decreaseValue() {
		if(this == DieValue.MIN_VALUE) {
			return this;
		} else {
			return DieValue.fromInt(this.toInt() - 1);
		}
	}

}