package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Nicolò Felicioni
 */

public class WindowPattern {

	private Space[][] spaces;
	private int difficulty;

	public static final int SPACES_LENGTH = 5;
	public static final int SPACES_HEIGTH = 4;
	public static final int MIN_DIFFICULTY = 3;
	public static final int MAX_DIFFICULTY = 6;


	/**
	 * Constructor
	 *
	 *
	 * @param spaces matrix of spaces
	 * @throws WindowPatternDimensionException if the dimension is wrong
	 * @throws UnboundDifficultyValueException if the difficulty value is wrong
	 *
	 */
	public WindowPattern(Space[][] spaces, int difficulty) throws WindowPatternDimensionException, UnboundDifficultyValueException {
		if(spaces.length != SPACES_HEIGTH)
			throw new WindowPatternDimensionException("Wrong number of raws");
		for(int i = 0; i < SPACES_HEIGTH ; i++){
			if(spaces[i].length != SPACES_LENGTH)
				throw new WindowPatternDimensionException("Wrong number of columns");
		}
		if(difficulty < MIN_DIFFICULTY)
			throw new UnboundDifficultyValueException("difficulty value too small");
		if(difficulty > MAX_DIFFICULTY)
			throw new UnboundDifficultyValueException("difficulty value too big");

		this.difficulty = difficulty;

		// TODO - boh
		this.spaces = spaces;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	/**
	 *
	 * @param p a point
	 *
	 * @return the corresponding space on the window pattern
	 */
	public Space getSpace(Point p){

		return spaces[p.getX()][p.getY()].cloneSpace();
	}

	/**
	 *
	 * @param x raw coordinate
	 * @param y column coordinate
	 * @throws NotValidPointException will be thrown if the space doesn't exist
	 * @return the corresponding space on the window pattern
	 */
	public Space getSpace(int x, int y) throws NotValidPointException {
		return getSpace(new Point(x, y));
	}

	/**
	 *
	 * @return a matrix with all the spaces of the window pattern
	 */

	public Space[][] getAllSpaces() {
		Space[][] newMatrix = new Space[4][5];

		for(int i = 0; i < SPACES_HEIGTH; i++){
			for(int j = 0; j < SPACES_LENGTH; j++){
				newMatrix[i][j] = spaces[i][j].cloneSpace();
			}
		}
		return newMatrix;
	}


	/**
	 *
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException  it will be thrown if the die is not placeable
	 */
	public void placeDie(Die die, Point p) throws PlacementException {

		if(isThereSomeDieAdjacent(p))
			spaces[p.getX()][p.getY()].placeDie(die);
		else throw new PlacementException("There isn't any adjacent die here");

	}



	/**
	 * Places a die on a space of the window pattern ignoring its color restrictions
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException  it will be thrown if the die is not placeable
	 */
	public void placeDieIgnoreColor(Die die, Point p) throws PlacementException {

		if(isThereSomeDieAdjacent(p)){
			spaces[p.getX()][p.getY()].placeDieIgnoreColor(die);
		}else
			throw new PlacementException("There isn't any adjacent die here");


	}

	/**
	 *Places a die on a space of the window pattern ignoring its value restrictions
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException
	 */
	public void placeDieIgnoreValue(Die die, Point p) throws PlacementException {

		if(isThereSomeDieAdjacent(p)){
			spaces[p.getX()][p.getY()].placeDieIgnoreColor(die);
		}else
			throw new PlacementException("There isn't any adjacent die here");

	}

	/**
	 *
	 * @param die a die
	 * @param x the raw where you want to place the die
	 * @param y the column where you want to place the die
	 */
	public void placeDie(Die die, int x, int y) throws PlacementException, NotValidPointException {

		Point p = new Point(x, y);


		//checks if there is some die adjacent to the point p
		if(isThereSomeDieAdjacent(p)){
			spaces[p.getX()][p.getY()].placeDie(die);
		}else
			throw new PlacementException("There isn't any adjacent die here");

	}

	/**
	 *Places a die on a space of the window pattern ignoring its color restrictions
	 * @param die a die
	 * @param x the raw where you want to place the die
	 * @param y the column where you want to place the die
	 * @throws NotValidPointException
	 * @throws PlacementException
	 */
	public void placeDieIgnoreColor(Die die, int x, int y) throws NotValidPointException, PlacementException {

		Point p = new Point(x, y);

		//TODO - credo si potrebbe fare in altro modo

		//checks if there is some die adjacent to the point p
		if(isThereSomeDieAdjacent(p)){

			spaces[x][y].placeDieIgnoreColor(die);

		}else
			//if there isn't a die adjacent to the point p
			throw new PlacementException("There's no adjacent die");
	}


	/**
	 *Places a die on a space of the window pattern ignoring its value restrictions
	 * @param die
	 * @param x the raw where you want to place the die
	 * @param y the column where you want to place the die
	 */
	public void placeDieIgnoreValue(Die die, int x, int y) throws NotValidPointException, PlacementException {
		Point p = new Point(x, y);


		//checks if there is some die adjacent to the point p
		if(isThereSomeDieAdjacent(p)){

			spaces[x][y].placeDieIgnoreValue(die);

		}else
			//if there isn't a die adjacent to the point p
			throw new PlacementException("There's no adjacent die");

	}



	/**
	 * checks if the die is placeable
	 * @param p
	 */
	public boolean isPlaceable(Die die, Point p) {

		//check color and value restrictions
		if(getSpace(p).respectAllRestrictions(die)){

			//check if there is another die adjacent (diagonally  or orthogonally)
			return isThereSomeDieAdjacent(p);
		}

		return false;
	}

	/**
	 * checks if the die is placeable
	 * @param x
	 * @param y
	 * @throws NotValidPointException if the point isn't valid
	 */
	public boolean isPlaceable(Die die, int x, int y) throws NotValidPointException {
		Point p = new Point(x, y);

		//check color and value restrictions

		if(getSpace(p).respectAllRestrictions(die)){
			//check if there is another die adjacent (diagonally  or orthogonally)
			return isThereSomeDieAdjacent(p);
		}

		return false;
	}


	/**
	 * checks if there is a die adjacent to the point p orthogonally or diagonally
	 * @param p a point
	 * @return true if there is a die adjacent to the point p, otherwise false
	 */
	private boolean isThereSomeDieAdjacent(Point p){

		return p.getAdjacentSpaces().stream().map(point -> getSpace(point)).filter(Space::hasDie).findAny().isPresent();

	}

	/**
	 * @param a
	 * @param b
	 */
	public void moveDie(Point a, Point b) throws NotValidMoveException{
		Die die = getSpace(a).getDie();

		try {
			removeDie(a);
		} catch (SpaceNotOccupiedException e) {
			throw new NotValidMoveException("There's no die in this point:" + a.getX() + ", " + a.getY());
		}

		try {
			placeDie(die, b);
		} catch (PlacementException e) {
			throw new NotValidMoveException("The point:" + a.getX() + ", " + a.getY() + "is already occupied");
		}
	}

	/**
	 * removes the die in point p
	 *
	 * @param p a point
	 * @throws SpaceNotOccupiedException if there isn't any die in the point p
	 *
	 */
	public void removeDie(Point p) throws SpaceNotOccupiedException {
		if(getSpace(p).hasDie())
			spaces[p.getX()][p.getY()].removeDie();
		else
			throw new SpaceNotOccupiedException("Not occupied: " + p.getX() + ", " + p.getY());
	}

	/**
	 * Getter row spaces.
	 *
	 * @param row the row.
	 * @return an array of spaces.
	 */
	public Space[] getSpacesRow (int row) {
		Space[] rowArray = new Space[SPACES_LENGTH];
		for(int i=0; i < SPACES_LENGTH; i ++) {
			rowArray[i] = this.spaces[row][i].cloneSpace();
		}
		return rowArray;
	}

	/**
	 * Getter column spaces.
	 *
	 * @param column the column.
	 * @return an array of spaces.
	 */
	public Space[] getSpacesColumn (int column) {
		Space[] columnArray = new Space[SPACES_HEIGTH];
		for(int i=0; i < SPACES_HEIGTH; i ++) {
			columnArray[i] = this.spaces[i][column].cloneSpace();
		}
		return columnArray;
	}



	public List<Space> getAllSpacesAsList(){
		ArrayList<Space> spaces;
		spaces = new ArrayList<>();

		for(int i = 0; i < SPACES_HEIGTH; i++){
			for(int j = 0; j < SPACES_LENGTH; j++){
				spaces.add(this.spaces[i][j].cloneSpace());
			}
		}
		return spaces;
	}



	public int getNumberOfDice(){
		return (int) getAllSpacesAsList().stream().filter(Space::hasDie).count();
	}

	public WindowPattern cloneWindowPattern() throws WindowPatternDimensionException, UnboundDifficultyValueException {
		return new WindowPattern(getAllSpaces(), this.difficulty);
	}


}