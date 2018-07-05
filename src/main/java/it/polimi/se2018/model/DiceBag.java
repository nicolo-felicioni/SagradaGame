package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.DiceBagEmptyException;
import it.polimi.se2018.exceptions.DiceBagException;
import it.polimi.se2018.exceptions.NotEnoughDiceException;
import it.polimi.se2018.exceptions.NotValidNumberOfDiceException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolò Felicioni
 */

public class DiceBag implements Serializable {


	/**
	 * the number of present dice.
	 */
	private ArrayList<Integer> numberOfPresentDice;

	/**
	 * Constant field. the initial number of each set of colored dice.
	 */
	private static final int INITIAL_NUMBER = 18;

	/**
	 * Constant field. the initial number of colors.
	 */
	private static final int NUMBER_OF_COLORS = 5;


	/**
	 * constructor.
	 */
	public DiceBag(){
		numberOfPresentDice = new ArrayList<>(NUMBER_OF_COLORS);
		for(int i = 0; i< NUMBER_OF_COLORS; i++)
			numberOfPresentDice.add(INITIAL_NUMBER);
	}


	/**
	 * copy constructor.
	 * @param copy a dice bag to be copied
	 */
	public DiceBag(DiceBag copy){
		this.numberOfPresentDice =  new ArrayList<>(NUMBER_OF_COLORS);
		this.numberOfPresentDice.addAll(copy.numberOfPresentDice);
	}

	/**
	 * choose a random die, remove it from the diceBag and return it
	 * @return randomDie, a random die
	 * @throws DiceBagEmptyException if Dice Bag is empty
	 */
	public Die drawDie() throws DiceBagException {
		if (this.isEmpty())
			throw new DiceBagEmptyException("Dice Bag is empty");
		DieColor color;
		DieValue value = DieValue.getRandom();

		do{
			color = DieColor.getRandom();
		}while(numberOfPresentDice.get(color.toInt())==0);

		int presentDiceBefore = numberOfPresentDice.get(color.toInt());

		numberOfPresentDice.set(color.toInt(), presentDiceBefore - 1);

		return new Die(color, value);

	}


	/**
	 * choose n random dice, remove them form the diceBag and return them
	 * @param n number of random dice that will be returned
	 * @return temp, an ArrayList with removed dice
	 */
	public List<Die> drawDice(int n) throws DiceBagException {
		if(n < 0)
			throw new NotValidNumberOfDiceException("Requested a negative number of dice");

		if (this.size() < n)
			throw new NotEnoughDiceException("Not enough dice in the dice bag");

		ArrayList<Die> dice = new ArrayList<>(n) ;

		for(int i = 0; i < n; i++ ){
			dice.add(this.drawDie());
		}
		return dice;
	}


	/**
	 * returns the size of the dice bag.
	 * @return the size of the dice bag.
	 */
	public int size() {
		return numberOfPresentDice.stream().mapToInt(Integer::intValue).sum();
	}

	public boolean isEmpty() {
		return numberOfPresentDice.stream().noneMatch(integer -> integer > 0);
	}

	/**
	 * Add a die to the dice bag.
	 * @param die the die that has to be added.
	 */
	public void addDie(Die die) throws DiceBagException{
		if(numberOfPresentDice.get(die.getColor().toInt()) != DiceBag.INITIAL_NUMBER) {
			this.numberOfPresentDice.set(die.getColor().toInt(), numberOfPresentDice.get(die.getColor().toInt()) + 1);
		} else {
			throw new DiceBagException("The die can not be added to the dice bag.");
		}
	}

	/**
	 * Clone method. It ruterns a new copy of this dice bag.
	 * @return a new copy of this dice bag.
	 */
	public DiceBag cloneDiceBag() {
		return new DiceBag(this);
	}

}