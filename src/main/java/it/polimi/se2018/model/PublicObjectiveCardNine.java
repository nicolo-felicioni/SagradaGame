package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;

import java.util.List;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardNine extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardNine.
	 */
	public static final String NAME = "Color Diagonals";

	/**
	 * Info of the PublicObjectiveCardNine.
	 */
	public static final String INFO = "Count of diagonally adjacent same-color dice";

	/**
	 * Points per die.
	 */
	public static final int POINTS_PER_DIE = 1;

	public PublicObjectiveCardNine() {
		super(NAME, INFO);
	}

	/**
	 * Calculate the points of a window pattern with this objective card.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @return the amount of points.
	 */
	@Override
	public int calculatePoints(WindowPattern windowPattern) {
		int numberOfDiagonalDice = 0;
		while(true) {
			Point point = this.getPointOfADice(windowPattern);
			if(point == null) {
				break;
			}else{
				int temp = this.getNumberOfDiagonalDiceWith(windowPattern, point);
				if(temp != 1) {
					numberOfDiagonalDice += temp;
				}
			}
		}
		return numberOfDiagonalDice;


	}

	/**
	 *
	 */
	private Point getPointOfADice(WindowPattern windowPattern) {
		for(int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
			for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
				try {
					Point p = new Point(i, j);
					if (windowPattern.getSpace(p).hasDie()) {
						return p;
					}
				}catch (NotValidPointException e) {
					return null;
				}
			}
		}
		return null;
	}

	/**
	 *
	 */
	private int getNumberOfDiagonalDiceWith (WindowPattern window, Point p) {
		List<Point> points = p.getDiagonalPoints();
		try{
			DieColor color = window.getSpace(p).getDie().getColor();
			window.removeDie(p);
			int numberOfValid = 0;
			for(Point point : points) {
				Space space = window.getSpace(point);
				if(space.hasDie() && color.equals(window.getSpace(point).getDie().getColor())) {
					numberOfValid += getNumberOfDiagonalDiceWith(window, point);
				}
			}
			return 1 +   numberOfValid;
		}catch(SpaceNotOccupiedException e) {
			return 0;
		}
	}

}