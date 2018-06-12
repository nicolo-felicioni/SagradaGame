package it.polimi.se2018.model;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.Serializable;
import java.util.Comparator;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * The Class of Die
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

	public String getValueUnicode(){
		switch (this.getValue().toInt()) {
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

	public static Comparator<Die> DieComparator= new Comparator<Die>() {
		@Override
		public int compare(Die o1, Die o2) {
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
		}
	};
	public String toString(){
		return("Die color: " + color + "\nDie value: " + value);
	}

}