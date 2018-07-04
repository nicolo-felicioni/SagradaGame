package it.polimi.se2018.model;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.Serializable;
import java.util.Comparator;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * The Class of die
 * @author PeiQing Gao
 */
public class Die implements Serializable {

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

	/**
	 * get a random die
	 * @return a random die
	 */
	public static Die getRandomDie(){
		return new Die(DieColor.getRandom(), DieValue.getRandom());
	}


	/**
	 * getter of the color of the die.
	 * @return the color of the die.
	 */
	public DieColor getColor() {
		return this.color;
	}

	/**
	 * getter of the value of the die.
	 * @return the value of the die.
	 */
	public DieValue getValue() {
		return this.value;
	}

	/**
	 * setter of the value of the die.
	 */
	public void setValue(DieValue value){
		this.value=value;
	}

	/**
	 * roll the die.
	 *
	 * the die will have a random value.
	 */
	public void roll() {
		this.value = DieValue.getRandom();
	}

	/**
	 * Indicates whether a die is "equal to" this one.
	 * @param die the die to be compared with this one
	 * @return true if the dice are equal.
	 */
	public boolean equalsDie(Die die){
		return ((this.color == die.color) && (this.value == die.value));
	}

	/**
	 * the comparator of the dice.
	 */
	public static final Comparator<Die> DieComparator = (o1, o2) -> {
        int die1Color=o1.getColor().toInt();
        int die2Color=o2.getColor().toInt();
        if (die1Color==die2Color){
            int die1Value=o1.getValue().toInt();
            int die2Value=o2.getValue().toInt();
            return die1Value-die2Value;
        }
        else{
            return die1Color-die2Color;
        }
    };


	public String toString(){
		return("die color: " + color + "\ndie value: " + value);
	}

	/**
	 * Return a die with the opposite value.
	 * @return a die with the opposite value.
	 */
	public Die flip() {
		return new Die(this.color, this.value.oppositeValue());
	}

	/**
	 * Return a clone of this die.
	 * @return a clone of this die.
	 */
	public Die cloneDie() {
		return new Die(this);
	}

    public String getValueUnicode() {
        return this.value.toUnicode();
    }
}