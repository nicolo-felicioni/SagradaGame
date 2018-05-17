package it.polimi.se2018.model;

/**
 * The Class of Die
 * @author PeiQing Gao
 */
public class Die{

	private final DieColor color;
	private DieValue value;

	/**
	 * Constructor with both color and value
	 * @param color
	 * @param value
	 */
	public Die(DieColor color, DieValue value) {
		this.color = color;
		this.value = value;
	}

	/**
	 * Copy constructor
	 *
	 */
	public Die(Die die) {
		this.color = die.getColor();
		this.value = die.getValue();
	}

	public DieColor getColor() {
		return this.color;
	}

	public DieValue getValue() {
		return this.value;
	}

	public void setValue(DieValue value){
		this.value=value;
	}
	public void roll() {
		this.value = DieValue.getRandom();
	}
	public boolean equalsDie(Die die){
		return ((this.color == die.color) && (this.value == die.value));
	}

	public String toString(){
		return("Die color: " + color + "\nDie value: " + value);
	}

}