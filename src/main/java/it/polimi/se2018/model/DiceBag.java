package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.DiceBagEmptyException;
import it.polimi.se2018.exceptions.NotEnoughDiceException;

import java.util.*;

public class DiceBag {

	private ArrayList<Die> diceBag;

	/**
	 * choose a random die, remove it from the diceBag and return it
	 * @return randomDie, a random die
	 * @throws DiceBagEmptyException if Dice Bag is empty
	 */
	public Die drawDie() throws DiceBagEmptyException {
		if (diceBag.isEmpty()) throw new DiceBagEmptyException("Dice Bag is empty");
		int randomNum = new Random().nextInt(diceBag.size());
		Die randomDie = diceBag.get(randomNum);
		diceBag.remove(randomNum);
		return randomDie;
	}

	/**
	 * choose n random dice, remove them form the diceBag and return them
	 * @param n
	 * @return temp, an ArrayList with removed dice
	 */
	public List<Die> drawDice(int n) throws NotEnoughDiceException {
		if (diceBag.size()<n)throw new NotEnoughDiceException("Not enought dice in the dice bag");
		ArrayList<Die> temp= new ArrayList<Die>() ;
		Die randomDie;
		int randomNum;
		for(int i=0;i<n;i++){
			randomNum = new Random().nextInt(diceBag.size());
			randomDie = diceBag.get(randomNum);
			diceBag.remove(randomNum);
		}
		return temp;
	}

	public int size() {
		return diceBag.size();
	}

	public boolean isEmpty() {
		return diceBag.isEmpty();
	}

}