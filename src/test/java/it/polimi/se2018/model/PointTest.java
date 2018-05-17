package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidPointException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author davide yi xian hu
 */
public class PointTest {

	private Point firstPoint;
	private Point lastPoint;
	private Point point;

	@Before
	public void setUp(){
		try {
			firstPoint = new Point(0, 0);
			lastPoint = new Point(3, 4);
			point = new Point(2, 3);
		} catch (NotValidPointException e) {

		}
	}

	@Test
	public void testIsEdgyPoint() {
		assertTrue(firstPoint.isEdgyPoint());
		assertTrue(lastPoint.isEdgyPoint());
		assertFalse(point.isEdgyPoint());
	}

	@Test
	public void testGetOrtogonalPoints() {
		List<Point> points = point.getOrtogonalPoints();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != point.getX() || listPoint.getY() != point.getY());
			assertTrue(1 == Math.abs(listPoint.getX() - point.getX() + listPoint.getY() - point.getY()));
		}
		points = firstPoint.getOrtogonalPoints();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != point.getX() || listPoint.getY() != firstPoint.getY());
			assertTrue(1 == Math.abs(listPoint.getX() - point.getX() + listPoint.getY() - firstPoint.getY()));
		}
		points = lastPoint.getOrtogonalPoints();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != lastPoint.getX() || listPoint.getY() != lastPoint.getY());
			assertTrue(1 == Math.abs(listPoint.getX() - lastPoint.getX() + listPoint.getY() - lastPoint.getY()));
		}
	}

	@Test
	public void testGetDiagonalPoints() {
		List<Point> points = point.getDiagonalPoints();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != point.getX() && listPoint.getY() != point.getY());
			assertTrue(2 == Math.abs(listPoint.getX() - point.getX()) +  Math.abs(listPoint.getY() - point.getY()));
		}
		points = firstPoint.getDiagonalPoints();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != firstPoint.getX() && listPoint.getY() != firstPoint.getY());
			assertTrue(2 == Math.abs(listPoint.getX() - firstPoint.getX()) +  Math.abs(listPoint.getY() - firstPoint.getY()));
		}
		points = lastPoint.getDiagonalPoints();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != lastPoint.getX() && listPoint.getY() != lastPoint.getY());
			assertTrue(2 == Math.abs(listPoint.getX() - lastPoint.getX()) +  Math.abs(listPoint.getY() - lastPoint.getY()));
		}
	}

	@Test
	public void testGetAdjacentSpaces() {
		List<Point> points = point.getAdjacentSpaces();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != point.getX() || listPoint.getY() != point.getY());
			assertTrue(2 == Math.abs(listPoint.getX() - point.getX()) +  Math.abs(listPoint.getY() - point.getY()) ||
					1 == Math.abs(listPoint.getX() - point.getX() + listPoint.getY() - point.getY()));
		}
		points = firstPoint.getAdjacentSpaces();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != firstPoint.getX() || listPoint.getY() != firstPoint.getY());
			assertTrue(2 == Math.abs(listPoint.getX() - firstPoint.getX()) +  Math.abs(listPoint.getY() - firstPoint.getY()) ||
					1 == Math.abs(listPoint.getX() - firstPoint.getX() + listPoint.getY() - firstPoint.getY()));		}
		points = lastPoint.getAdjacentSpaces();
		for (Point listPoint : points) {
			assertTrue(listPoint.getX() != lastPoint.getX() || listPoint.getY() != lastPoint.getY());
			assertTrue(2 == Math.abs(listPoint.getX() - lastPoint.getX()) +  Math.abs(listPoint.getY() - lastPoint.getY()) ||
					1 == Math.abs(listPoint.getX() - lastPoint.getX() + listPoint.getY() - lastPoint.getY()));
		}
	}

	@Test
	public void testEqualsPoint() {
		assertFalse(point.equalsPoint(firstPoint));
		try {
			assertTrue(point.equalsPoint(new Point(2, 3)));
		} catch (NotValidPointException e) {

		}
	}

	@Test
	public void testClonePoint() {
		Point p = point.clonePoint();
		assertTrue(p.equalsPoint(point));
		assertTrue(p.getX() == p.getX());
		assertTrue(p.getY() == p.getY());
	}
}