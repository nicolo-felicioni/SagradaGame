package it.polimi.se2018.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author davide yi xian hu
 */
public class PublicObjectiveCardTenTest {

	private WindowPattern window;
	private PublicObjectiveCardTen card;

	@Before
	public void setUp(){
		card = new PublicObjectiveCardTen();
	}

	@After
	public void tearDown(){
		window = null;
	}

	@Test(expected = NullPointerException.class)
	public void testCalculatePoints1() {
		card.calculatePoints(window);
		fail();
	}

	@Test
	public void testCalculatePoints2() {
		try {
			Space[][] spaces = new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];
			for (int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
				for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
					spaces[i][j] = new BlankSpace();
				}
			}
			spaces[0][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[0][1].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[0][2].placeDie(new Die(DieColor.GREEN, DieValue.ONE));
			spaces[0][3].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[0][4].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][1].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[1][2].placeDie(new Die(DieColor.GREEN, DieValue.ONE));
			spaces[1][3].placeDie(new Die(DieColor.YELLOW, DieValue.ONE));
			spaces[1][4].placeDie(new Die(DieColor.PURPLE, DieValue.ONE));
			spaces[2][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[2][1].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[2][2].placeDie(new Die(DieColor.YELLOW, DieValue.ONE));
			spaces[2][3].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[2][4].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[3][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[3][1].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[3][2].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[3][3].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[3][4].placeDie(new Die(DieColor.RED, DieValue.ONE));
			window = new WindowPattern(spaces, 4, "pattern");
		} catch (Exception e) {
			fail();
		}
		assertEquals(4, card.calculatePoints(window));
	}

	@Test
	public void testCalculatePoints3() {
		try {
			Space[][] spaces = new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];
			for (int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
				for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
					spaces[i][j] = new BlankSpace();
				}
			}
			spaces[0][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[0][1].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[0][2].placeDie(new Die(DieColor.GREEN, DieValue.ONE));
			spaces[0][3].placeDie(new Die(DieColor.YELLOW, DieValue.ONE));
			spaces[0][4].placeDie(new Die(DieColor.PURPLE, DieValue.ONE));
			spaces[1][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][1].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[1][2].placeDie(new Die(DieColor.GREEN, DieValue.ONE));
			spaces[1][3].placeDie(new Die(DieColor.YELLOW, DieValue.ONE));
			spaces[1][4].placeDie(new Die(DieColor.PURPLE, DieValue.ONE));
			spaces[2][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[2][1].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[2][2].placeDie(new Die(DieColor.GREEN, DieValue.ONE));
			spaces[2][3].placeDie(new Die(DieColor.YELLOW, DieValue.ONE));
			spaces[2][4].placeDie(new Die(DieColor.PURPLE, DieValue.ONE));
			spaces[3][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[3][1].placeDie(new Die(DieColor.RED, DieValue.ONE));
			spaces[3][2].placeDie(new Die(DieColor.GREEN, DieValue.ONE));
			spaces[3][3].placeDie(new Die(DieColor.YELLOW, DieValue.ONE));
			spaces[3][4].placeDie(new Die(DieColor.PURPLE, DieValue.ONE));
			window = new WindowPattern(spaces, 4, "pattern");
		} catch (Exception e) {
			fail();
		}
		assertEquals(16, card.calculatePoints(window));
	}

	@Test
	public void testCalculatePoints4() {
		try {
			Space[][] spaces = new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];
			for (int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
				for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
					spaces[i][j] = new BlankSpace();
				}
			}
			spaces[0][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[0][1].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[0][2].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[0][3].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[0][4].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][1].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][2].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][3].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[1][4].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[2][0].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[2][1].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[2][2].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[2][3].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			spaces[2][4].placeDie(new Die(DieColor.BLUE, DieValue.ONE));
			window = new WindowPattern(spaces, 4, "pattern");
		} catch (Exception e) {
			fail();
		}
		assertEquals(0, card.calculatePoints(window));
	}
}