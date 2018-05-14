package it.polimi.se2018.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author davide yi xian hu
 */
public class TestPublicObjectiveCardSeven {
	private WindowPattern window;
	private PublicObjectiveCardSeven card;

	@Before
	public void setUp(){
		card = new PublicObjectiveCardSeven();
	}

	@Test
	public void tesCalculatePoints1() {
		try{
			card.calculatePoints(window);
			fail();
		}catch(NullPointerException e) {
		}
	}

	@Test
	public void tesCalculatePoints2() {
		try {
			Space[][] spaces = new Space[WindowPattern.SPACES_HEIGTH][WindowPattern.SPACES_LENGTH];
			for (int i = 0; i < WindowPattern.SPACES_HEIGTH; i++) {
				for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
					spaces[i][j] = new BlankSpace();
				}
			}
			spaces[0][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][4].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			window = new WindowPattern(spaces, 4);
		}catch(Exception e){
			fail();
		}
		assertEquals(2,card.calculatePoints(window));
	}

	@Test
	public void tesCalculatePoints3() {
		try {
			Space[][] spaces = new Space[WindowPattern.SPACES_HEIGTH][WindowPattern.SPACES_LENGTH];
			for (int i = 0; i < WindowPattern.SPACES_HEIGTH; i++) {
				for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
					spaces[i][j] = new BlankSpace();
				}
			}
			spaces[0][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][1].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[0][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][0].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[2][1].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[2][2].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[2][3].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[2][4].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[3][0].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[3][1].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[3][2].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			spaces[3][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][4].placeDie(new Die(DieColor.BLUE, DieValue.SIX));
			window = new WindowPattern(spaces, 4);
		}catch(Exception e){
			fail();
		}
		assertEquals(20,card.calculatePoints(window));
	}

	@Test
	public void tesCalculatePoints4() {
		try {
			Space[][] spaces = new Space[WindowPattern.SPACES_HEIGTH][WindowPattern.SPACES_LENGTH];
			for (int i = 0; i < WindowPattern.SPACES_HEIGTH; i++) {
				for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
					spaces[i][j] = new BlankSpace();
				}
			}
			spaces[0][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[0][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[1][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[2][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][0].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][1].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][2].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][3].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			spaces[3][4].placeDie(new Die(DieColor.BLUE, DieValue.FIVE));
			window = new WindowPattern(spaces, 4);
		}catch(Exception e){
			fail();
		}
		assertEquals(0,card.calculatePoints(window));
	}
}
