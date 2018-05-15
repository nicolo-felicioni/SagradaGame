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
	private int dieValue;
	DieValue(int dieValue){
		this.dieValue=dieValue;
	}
	public int toInt(){
			return this.dieValue;
	}
	public static DieValue fromInt(int n){
		for (DieValue value : DieValue.values()) {
			if (value.dieValue == n) {
				return value;
			}
		}
		return null;
	}
	public static DieValue getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}


}