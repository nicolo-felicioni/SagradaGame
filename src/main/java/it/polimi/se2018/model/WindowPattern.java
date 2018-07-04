package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolò Felicioni
 */

public class WindowPattern implements Serializable {

	private Space[][] spaces;
	private int difficulty;

	public static final int SPACES_LENGTH = 5;
	public static final int SPACES_HEIGHT = 4;
	public static final int MIN_DIFFICULTY = 3;
	public static final int MAX_DIFFICULTY = 6;

	/**
	 * Window pattern name.
	 */
	private String name;


	/**
	 * Constructor
	 *
	 *
	 * @param spaces matrix of spaces.
	 * @param difficulty the difficulty of the pattern.
	 * @param name the window pattern name.
	 * @throws WindowPatternDimensionException if the dimension is wrong
	 * @throws UnboundDifficultyValueException if the difficulty value is wrong
	 *
	 */
	public WindowPattern(Space[][] spaces, int difficulty, String name) throws WindowPatternDimensionException, UnboundDifficultyValueException {
		if(spaces.length != SPACES_HEIGHT)
			throw new WindowPatternDimensionException("Wrong number of rows");
		for(int i = 0; i < SPACES_HEIGHT; i++){
			if(spaces[i].length != SPACES_LENGTH)
				throw new WindowPatternDimensionException("Wrong number of columns");
		}
		if(difficulty < MIN_DIFFICULTY)
			throw new UnboundDifficultyValueException("difficulty value too small");
		if(difficulty > MAX_DIFFICULTY)
			throw new UnboundDifficultyValueException("difficulty value too big");

		this.difficulty = difficulty;

		this.spaces = new Space[SPACES_HEIGHT][SPACES_LENGTH];

		for(int i = 0; i < SPACES_HEIGHT; i++)
			for(int j=0; j < SPACES_LENGTH; j++)
				this.spaces[i][j]=spaces[i][j].cloneSpace();
		this.name = name;
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

		for(int i = 0; i < SPACES_HEIGHT; i++){
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
	public boolean isPlaceable(Die die, Point p) {
		if(this.getNumberOfDice() == 0)
			return p.isEdgyPoint() && getSpace(p).respectAllRestrictions(die);
		else
			return (this.isThereSomeDieAdjacent(p) &&
					p.getOrtogonalPoints().stream().filter(point -> this.getSpace(point).hasDie())
                            .noneMatch(point -> this.getSpace(point).getDie().getColor()==die.getColor() ||
                                    this.getSpace(point).getDie().getValue()==die.getValue()) &&
					getSpace(p).respectAllRestrictions(die) )&& !getSpace(p).hasDie();


	}

	/**
	 * Checks if the die is placeable for the window pattern ignoring color restrictions.
	 * If the window is empty, this method checks if the point is on the edge of the window,
	 * otherwise it will check if there is any adjacent die to the point p.
	 * Ignoring color restrictions.
	 *
	 * @param die  the die.
	 * @param p a point.
	 */
	public boolean isPlaceableIgnoreColor(Die die, Point p) {
		if(this.getNumberOfDice() == 0) {
			if (getSpace(p).isValueRestricted())
				return p.isEdgyPoint() && getSpace(p).getValueRestriction() == die.getValue();
			else
				return p.isEdgyPoint();
		} else
			if (getSpace(p).isValueRestricted())
				return (this.isThereSomeDieAdjacent(p) &&
						p.getOrtogonalPoints().stream().filter(point -> this.getSpace(point).hasDie())
								.noneMatch(point -> this.getSpace(point).getDie().getColor()==die.getColor() ||
										this.getSpace(point).getDie().getValue()==die.getValue()) &&
						getSpace(p).getValueRestriction() == die.getValue()) && !getSpace(p).hasDie();
			else
				return (this.isThereSomeDieAdjacent(p) &&
						p.getOrtogonalPoints().stream().filter(point -> this.getSpace(point).hasDie())
								.noneMatch(point -> this.getSpace(point).getDie().getColor()==die.getColor() ||
										this.getSpace(point).getDie().getValue()==die.getValue())) && !getSpace(p).hasDie();

	}

	/**
	 * Checks if the die is placeable for the window pattern ignoring value restrictions.
	 * If the window is empty, this method checks if the point is on the edge of the window,
	 * otherwise it will check if there is any adjacent die to the point p.
	 * Ignoring value restrictions.
	 *
	 * @param die  the die.
	 * @param p a point.
	 */
	public boolean isPlaceableIgnoreValue(Die die, Point p) {
		if(this.getNumberOfDice() == 0) {
			if (getSpace(p).isColorRestricted())
				return p.isEdgyPoint() && getSpace(p).getColorRestriction() == die.getColor();
			else
				return p.isEdgyPoint();
		} else
		if (getSpace(p).isColorRestricted())
			return (this.isThereSomeDieAdjacent(p) &&
					p.getOrtogonalPoints().stream().filter(point -> this.getSpace(point).hasDie())
							.noneMatch(point -> this.getSpace(point).getDie().getColor()==die.getColor() ||
									this.getSpace(point).getDie().getValue()==die.getValue()) &&
					getSpace(p).getColorRestriction() == die.getColor()) && !getSpace(p).hasDie();
		else
			return (this.isThereSomeDieAdjacent(p) &&
					p.getOrtogonalPoints().stream().filter(point -> this.getSpace(point).hasDie())
							.noneMatch(point -> this.getSpace(point).getDie().getColor()==die.getColor() ||
									this.getSpace(point).getDie().getValue()==die.getValue())) && !getSpace(p).hasDie();

	}


	/**
	 * Checks if the die is placeable for the window pattern and there's no die in adjacent spaces.
	 * If the window is empty, this method checks if the point is on the edge of the window,
	 * otherwise it will check if there is any adjacent die to the point p.
	 *
	 * @param die  the die.
	 * @param p a point.
	 */
	public boolean isPlaceableNoAdjacent(Die die, Point p) {
		if(this.getNumberOfDice() == 0)
			return p.isEdgyPoint() && getSpace(p).respectAllRestrictions(die);
		else
			return !this.isThereSomeDieAdjacent(p) && getSpace(p).respectAllRestrictions(die) && !getSpace(p).hasDie();


	}

	/**
	 *
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException  it will be thrown if the die is not placeable
	 */
	public void placeDie(Die die, Point p) throws PlacementException {



		if(isPlaceable(die, p)){

			spaces[p.getX()][p.getY()].placeDie(die);

		}

		else
			throw new PlacementException("die not placeable due to window restrictions");

	}

	/**
	 *
	 * @param die a die
	 * @param x the row where you want to place the die
	 * @param y the column where you want to place the die
	 */
	public void placeDie(Die die, int x, int y) throws PlacementException, NotValidPointException {

		Point p = new Point(x, y);
		if(isPlaceable(die, p))
			spaces[p.getX()][p.getY()].placeDie(die);
		else
			throw new PlacementException("die not placeable due to window restrictions");


	}


	/**
	 * Places a die on a space of the window ignoring the space's color restrictions
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException  it will be thrown if the die is not placeable
	 */
	public void placeDieIgnoreColor(Die die, Point p) throws PlacementException {

		if(isPlaceableIgnoreColor(die, p))
			spaces[p.getX()][p.getY()].placeDieIgnoreColor(die);
		else
			throw new PlacementException("die not placeable due to window restrictions");

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

		if(isPlaceableIgnoreColor(die, p))
			spaces[p.getX()][p.getY()].placeDieIgnoreColor(die);
		else
			throw new PlacementException("die not placeable due to window restrictions");
	}

	/**
	 *Places a die on a space of the window pattern ignoring the space's value restrictions
	 * @param die a die
	 * @param p the point where you want to place the die
	 * @throws PlacementException if the die isn't placeable
	 */
	public void placeDieIgnoreValue(Die die, Point p) throws PlacementException {

		if(isPlaceableIgnoreValue(die, p))
			spaces[p.getX()][p.getY()].placeDieIgnoreValue(die);
		else
			throw new PlacementException("die not placeable due to window restrictions");

	}


	/**
	 *Places a die on a space of the window pattern ignoring the space's value restrictions
	 * @param die a die
	 * @param x the row where you want to place the die
	 * @param y the column where you want to place the die
	 */
	public void placeDieIgnoreValue(Die die, int x, int y) throws NotValidPointException, PlacementException {
		Point p = new Point(x, y);




		if(isPlaceableIgnoreValue(die, p))
			spaces[p.getX()][p.getY()].placeDieIgnoreValue(die);
		else
			throw new PlacementException("die not placeable due to window restrictions");

	}

	/**
	 * Place a die on a space of the window pattern. The spaces adjacent to the space where the die will be placed must not have a die.
	 * @param die the die that have to be placed.
	 * @param p the coordinates of the space.
	 * @throws PlacementException if the die can not be placed.
	 */
	public void placeDieIgnoreAdjacent(Die die, Point p) throws PlacementException {
		if(isPlaceableNoAdjacent(die, p)) {
			spaces[p.getX()][p.getY()].placeDie(die);
		} else {
			throw new PlacementException("die not placeable because there's a die in adjacent position.");
		}
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
	 * checks if is possible to move a die from a point of the window to another.
	 * if the first point has a die and it is placeable on the second point return true.
	 * if the first point hasn't a die or the die on the first point can't be placed on the second point
	 * return false.
	 * @param a the first point
	 * @param b the second point
	 * @return true if the first point has a die and it is placeable on the second point
	 */
	private boolean isPossibleMoveDie(Point a, Point b){
		Die die;
		WindowPattern copy = this.cloneWindowPattern();

		try {
			die = copy.removeDie(a);
			copy.placeDie(die, b);
		} catch (PlacementException e) {
			return false;
		}
		return true;
	}

	/**
	 * Moves the die from a point to another.
	 * @param a the first point of the window
	 * @param b the second point of the window
	 * @throws NotValidMoveException if the first point is empty or the second point has already a die
	 */
	public void moveDie(Point a, Point b) throws NotValidMoveException{
		Die die;

		if(this.isPossibleMoveDie(a, b)){
			try {
				die = this.removeDie(a);
				this.placeDie(die, b);
			} catch (PlacementException e) {
				//impossible
				throw new NotValidMoveException("Impossible to move the die");
			}
		}else
			throw new NotValidMoveException("Impossible to move the die");

	}

	/**
	 * removes the die in point p
	 *
	 * @param p a point
	 * @throws SpaceNotOccupiedException if there isn't any die in the point p
	 *
	 */
	public Die removeDie(Point p) throws SpaceNotOccupiedException {

		if(getSpace(p).hasDie())
			return spaces[p.getX()][p.getY()].removeDie();
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
		Space[] columnArray = new Space[SPACES_HEIGHT];
		for(int i = 0; i < SPACES_HEIGHT; i ++) {
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

		for(int i = 0; i < SPACES_HEIGHT; i++){
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
	 * Return the name of the window pattern.
	 * @return the name of the window pattern.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @return a copy of the window pattern
	 * @throws WindowPatternDimensionException
	 * @throws UnboundDifficultyValueException
	 */
	public WindowPattern cloneWindowPattern() {

		try {
			return new WindowPattern(getAllSpaces(), this.difficulty, this.name);
		} catch (WindowPatternDimensionException e) {

		} catch (UnboundDifficultyValueException e) {
		}
		return null;
	}

	/**
	 * this method compares this pattern to another and returns true if they're equals.
	 *
	 * @param pattern a given window pattern
	 * @return true if the two window pattern are the same
	 */
	public boolean equalsWindowPattern(WindowPattern pattern){
		for(int i = 0; i<WindowPattern.SPACES_HEIGHT; i++){
			for(int j=0; j<WindowPattern.SPACES_LENGTH; j++) {
				try {
					if(! this.getSpace(i, j).equalsSpace(pattern.getSpace(i, j)))
						return false;
				} catch (NotValidPointException e) {
					//impossible
				}
			}
		}
		return (this.getDifficulty() == pattern.getDifficulty());
	}

}