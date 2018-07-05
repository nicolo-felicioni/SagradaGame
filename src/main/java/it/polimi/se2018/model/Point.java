package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidPointException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Davide Yi Xian Hu
 */
public class Point implements Serializable {

	/**
	 * X coordinate.
	 */
	private int x;

	/** Y coordinate.
	 *
	 */
	private int y;

	/** Minimum value of x.
	 *
	 */
	public static final int X_MIN = 0;

	/** Maximum value of x.
	 *
	 */
	public static final int X_MAX = 3;

	/** Minimum value of y.
	 *
	 */
	public static final int Y_MIN = 0;

	/** Maximum value of y.
	 *
	 */
	public static final int Y_MAX = 4;


	public boolean isEdgyPoint(){
		if(getX() == X_MIN || getX() == X_MAX|| getY() == Y_MIN || getY() == Y_MAX)
			return true;
		else
			return false;
	}

	/**
	 * Constructor of the Point class.
	 *
	 * @param x x coordinate.
	 * @param y y coordinate.
	 * @throws NotValidPointException if x or y is not a valid argument.
	 */
	public Point(int x, int y) throws NotValidPointException{
		if(x < X_MIN || x > X_MAX) {
			throw new NotValidPointException("x parameter should be between " + X_MIN + "and " + X_MAX);
		}else{
			this.x = x;
		}
		if(y < Y_MIN || y > Y_MAX) {
			throw new NotValidPointException("y parameter should be between " + Y_MIN + "and " + Y_MAX);
		}else{
			this.y = y;
		}
	}

	/**
	 * Getter of x.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Getter of y.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Getter of ortogonal points.
	 *
	 * @return a list of points.
	 */
	public List<Point> getOrtogonalPoints() {
		List<Point> ortoganalPoints = new ArrayList<>();
		try {
			ortoganalPoints.add(new Point(x - 1, y));
		} catch (NotValidPointException e) { }
		try {
			ortoganalPoints.add(new Point(x + 1, y));
		} catch (NotValidPointException e) { }
		try {
			ortoganalPoints.add(new Point(x, y - 1));
		} catch (NotValidPointException e) { }
		try {
			ortoganalPoints.add(new Point(x, y + 1));
		} catch (NotValidPointException e) { }
		return ortoganalPoints;
	}

	/**
	 * Getter of diagonal points.
	 *
	 * @return a list of points.
	 */
	public List<Point> getDiagonalPoints() {
		List<Point> diagonalPoints = new ArrayList<Point>();
		try {
			diagonalPoints.add(new Point(x - 1, y - 1));
		} catch (NotValidPointException e) { }
		try {
			diagonalPoints.add(new Point(x - 1, y + 1));
		} catch (NotValidPointException e) { }
		try {
			diagonalPoints.add(new Point(x + 1, y - 1));
		} catch (NotValidPointException e) { }
		try {
			diagonalPoints.add(new Point(x + 1, y + 1));
		} catch (NotValidPointException e) { }
		return diagonalPoints;
	}

	/**
	 * Getter of adjacent points.
	 *
	 * @return a list of points.
	 */
	public List<Point> getAdjacentSpaces () {
		List<Point> adjacentPoints = new ArrayList<Point>();
		adjacentPoints.addAll(getDiagonalPoints());
		adjacentPoints.addAll(getOrtogonalPoints());
		return adjacentPoints;
	}

	/**
	 * Compare this point with another point.
	 *
	 * @return true if the points are equals.
	 */
	public boolean equalsPoint(Point p) {
		return this.getX() == p.getX() && this.getY() == p.getY();
	}

	/**
	 * Clone this point.
	 *
	 * @return a clone of this point.
	 */
	public Point clonePoint() {
		try {
			return new Point(this.getX(), this.getY());
		} catch (NotValidPointException e) {
			return null;
		}
	}

	public String toString() {
		return "Point: X: " + x + ", Y: " + y;
	}
}