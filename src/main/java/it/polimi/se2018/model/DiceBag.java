package it.polimi.se2018.model;
/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.DiceBagEmptyException;
import it.polimi.se2018.exceptions.DiceBagException;
import it.polimi.se2018.exceptions.NotEnoughDiceException;
import it.polimi.se2018.exceptions.NotValidNumberOfDiceException;

import java.util.*;

public class DiceBag {


	private ArrayList<Integer> numberOfPresentDice;

	private final static int INITIAL_NUMBER = 18;
	private final static int NUMBER_OF_COLORS = 5;


	public DiceBag(){
		numberOfPresentDice = new ArrayList<Integer>(NUMBER_OF_COLORS);
		for(int i = 0; i< NUMBER_OF_COLORS; i++)
			numberOfPresentDice.add(INITIAL_NUMBER);
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
	 * @param n
	 * @return temp, an ArrayList with removed dice
	 */

	public List<Die> drawDice(int n) throws DiceBagException {
		if(n < 0)
			throw new NotValidNumberOfDiceException("Requested a negative number of dice");

		if (this.size() < n)
			throw new NotEnoughDiceException("Not enough dice in the dice bag");

		ArrayList<Die> dice = new ArrayList<Die>(n) ;

		for(int i = 0; i < n; i++ ){
			dice.add(this.drawDie());
		}
		return dice;
	}


	public int size() {
		return numberOfPresentDice.stream().mapToInt(Integer::intValue).sum();
	}

	public boolean isEmpty() {
		return numberOfPresentDice.stream().noneMatch(integer -> integer > 0);
	}

}