package it.polimi.se2018.model;

/**
 * @author PeiQing Gao
 */
public enum DieValue {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6);
	private int dievalue;

	/**
	 *
	 * @param dieValue
	 */
	DieValue(int dieValue){
		this.dievalue =dieValue;
	}

	/**
	 *
	 * @return dievalue
	 */
	public int toInt(){
			return this.dievalue;
	}

	/**
	 *
	 * @param n
	 * @return corresponding Die Value of input n
	 */
	public static DieValue fromInt(int n){
		for (DieValue value : DieValue.values()) {
			if (value.dievalue == n) {
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


}