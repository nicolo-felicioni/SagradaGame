package it.polimi.se2018.model;

import java.io.Serializable;

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
	private static DieValue MAX_VALUE;

	/**
	 * The lowest value.
	 */
	private static DieValue MIN_VALUE;

	private int dieNumber;
	private String unicodeValue;

	/**
	 *
	 * @param dieValue
	 */
	DieValue(int dieValue, String unicodeValue){
		this.dieNumber =dieValue;
		this.unicodeValue=unicodeValue;
	}

	/**
	 *
	 * @return dieNumber
	 */
	public int toInt(){
			return this.dieNumber;
	}

	public String toUnicode(){
		return this.unicodeValue;
	}

	/**
	 *
	 * @param n
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
	 *
	 * @return a random die Value
	 */
	public static DieValue getRandom() {
		return values()[(int) (Math.random() * values().length)];
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