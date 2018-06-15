package it.polimi.se2018.model;

import java.io.Serializable;

/**
 * @author PeiQing Gao
 */
public enum DieValue implements Serializable {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6);

	/**
	 * The highest value.
	 */
	private static DieValue MAX_VALUE;

	/**
	 * The lowest value.
	 */
	private static DieValue MIN_VALUE;

	private int dieNumber;

	/**
	 *
	 * @param dieValue
	 */
	DieValue(int dieValue){
		this.dieNumber =dieValue;
	}

	/**
	 *
	 * @return dieNumber
	 */
	public int toInt(){
			return this.dieNumber;
	}

	public String toUnicode(){
		switch (this.toInt()) {
			case 1:
				return "\u2680";

			case 2:
				return "\u2681" ;

			case 3:
				return "\u2682";

			case 4:
				return "\u2683";

			case 5:
				return "\u2684";

			case 6:
				return "\u2685";

		}
		return null;
	}

	/**
	 *
	 * @param n
	 * @return corresponding Die Value of input n
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
	 * @return a random Die Value
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