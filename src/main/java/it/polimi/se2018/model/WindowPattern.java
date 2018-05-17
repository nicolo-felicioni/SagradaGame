package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;

import java.util.ArrayList;
import java.util.List;

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
			throw new WindowPatternDimensionException("Wrong number of rows");
		for(int i = 0; i < SPACES_HEIGTH ; i++){
			if(spaces[i].length != SPACES_LENGTH)
				throw new WindowPatternDimensionException("Wrong number of columns");
		}
		if(difficulty < MIN_DIFFICULTY)
			throw new UnboundDifficultyValueException("difficulty value too small");
		if(difficulty > MAX_DIFFICULTY)
			throw new UnboundDifficultyValueException("difficulty value too big");

		this.difficulty = difficulty;

		this.spaces = new Space[SPACES_HEIGTH][SPACES_LENGTH];

		for(int i=0; i < SPACES_HEIGTH; i++)
			for(int j=0; j < SPACES_LENGTH; j++)
				this.spaces[i][j]=spaces[i][j].cloneSpace();
	}

	/**
	 *
	 * @return the difficulty of the window pattern
	 */
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
	 * @param x row coordinate
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
	 * checks if the die is placeable for the window pattern.
	 * If the window is empty, this method checks if the point is on the edge of the window,
	 * otherwise it will check if there is any adjacent die to the point p.
	 * @param p a point
	 */
	private boolean isPlaceable(Point p) {
		if(this.getNumberOfDice() == 0)
			return p.isEdgyPoint();
		else
			return isThereSomeDieAdjacent(p);
	}

	/**
	 * checks if the die is placeable for the window pattern.
	 * If the window is empty, this method checks if the point is on the edge of the window,
	 * otherwise it will check if there is any adjacent die to the point at the coordinates x and y.
	 * @param x first coordinate of the point
	 * @param y second coordinate of the point
	 * @throws NotValidPointException if the point isn't valid
	 */
	private boolean isPlaceable(int x, int y) throws NotValidPointException {
		Point p = new Point(x, y);

		if(this.getNumberOfDice() == 0)
			return p.isEdgyPoint();
		else
			return isThereSomeDieAdjacent(p);
	}


	/**
	 *
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException  it will be thrown if the die is not placeable
	 */
	public void placeDie(Die die, Point p) throws PlacementException {

		if(isPlaceable(p))
			spaces[p.getX()][p.getY()].placeDie(die);
		else
			throw new PlacementException("Die not placeable due to window restrictions");
	}

	/**
	 *
	 * @param die a die
	 * @param x the row where you want to place the die
	 * @param y the column where you want to place the die
	 */
	public void placeDie(Die die, int x, int y) throws PlacementException, NotValidPointException {

		Point p = new Point(x, y);
		if(isPlaceable(p))
			spaces[p.getX()][p.getY()].placeDie(die);
		else
			throw new PlacementException("Die not placeable due to window restrictions");


	}


	/**
	 * Places a die on a space of the window ignoring the space's color restrictions
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException  it will be thrown if the die is not placeable
	 */
	public void placeDieIgnoreColor(Die die, Point p) throws PlacementException {

		if(isPlaceable(p))
			spaces[p.getX()][p.getY()].placeDieIgnoreColor(die);
		else
			throw new PlacementException("Die not placeable due to window restrictions");

	}

	/**
	 *Places a die on a space of the window ignoring the space's color restrictions
	 * @param die a die
	 * @param x the row where you want to place the die
	 * @param y the column where you want to place the die
	 * @throws NotValidPointException if the point is not valid
	 * @throws PlacementException if the die isn't placeable
	 */
	public void placeDieIgnoreColor(Die die, int x, int y) throws NotValidPointException, PlacementException {

		Point p = new Point(x, y);

		if(isPlaceable(p))
			spaces[p.getX()][p.getY()].placeDieIgnoreColor(die);
		else
			throw new PlacementException("Die not placeable due to window restrictions");
	}

	/**
	 *Places a die on a space of the window pattern ignoring the space's value restrictions
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException if the die isn't placeable
	 */
	public void placeDieIgnoreValue(Die die, Point p) throws PlacementException {

		if(isPlaceable(p))
			spaces[p.getX()][p.getY()].placeDieIgnoreValue(die);
		else
			throw new PlacementException("Die not placeable due to window restrictions");

	}


	/**
	 *Places a die on a space of the window pattern ignoring the space's value restrictions
	 * @param die a die
	 * @param x the row where you want to place the die
	 * @param y the column where you want to place the die
	 */
	public void placeDieIgnoreValue(Die die, int x, int y) throws NotValidPointException, PlacementException {
		Point p = new Point(x, y);


		if(isPlaceable(p))
			spaces[p.getX()][p.getY()].placeDieIgnoreValue(die);
		else
			throw new PlacementException("Die not placeable due to window restrictions");

	}






	/**
	 * checks if there is a die adjacent to the point p orthogonally or diagonally
	 * @param p a point
	 * @return true if there is a die adjacent to the point p, otherwise false
	 */
	private boolean isThereSomeDieAdjacent(Point p){

		return p.getAdjacentSpaces().stream().map(this::getSpace).anyMatch(Space::hasDie);

	}

	/**
	 * Moves the die from a point to another.
	 * @param a the first point
	 * @param b the second point
	 * @throws NotValidMoveException if the first point is empty or the second point has already a die
	 */
	public void moveDie(Point a, Point b) throws NotValidMoveException{
		Die die = getSpace(a).getDie();

		try {
			removeDie(a);
		} catch (SpaceNotOccupiedException e) {
			throw new NotValidMoveException("There's no die in this point:" + a );
		}

		try {
			placeDie(die, b);
		} catch (PlacementException e) {
			throw new NotValidMoveException(b + "is already occupied");
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



	/**
	 *
	 * @return a list with all the spaces of the window pattern
	 */
	public List<Space> getAllSpacesAsList(){
		ArrayList<Space> listOfSpaces;
		listOfSpaces = new ArrayList<>();

		for(int i = 0; i < SPACES_HEIGTH; i++){
			for(int j = 0; j < SPACES_LENGTH; j++){
				listOfSpaces.add(this.spaces[i][j].cloneSpace());
			}
		}
		return listOfSpaces;
	}


	/**
	 *
	 * @return the number of dice present on the window
	 *
	 */
	public int getNumberOfDice(){
		return (int) getAllSpacesAsList().stream().filter(Space::hasDie).count();
	}


	/**
	 *
	 * @return a copy of the window pattern
	 * @throws WindowPatternDimensionException
	 * @throws UnboundDifficultyValueException
	 */
	public WindowPattern cloneWindowPattern() throws WindowPatternDimensionException, UnboundDifficultyValueException {
		return new WindowPattern(getAllSpaces(), this.difficulty);
	}


}