package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ToolCardStateException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardOneTest {
	private ToolCardOne activeCard;
	private ToolCardOne inactiveCard;

	@Before
	public void setUp(){
		activeCard = new ToolCardOne();
		inactiveCard = new ToolCardOne();
		try {
			activeCard.activate();
		} catch (ToolCardStateException e) {

		}
	}

	@Test
	public void testIncreaseDieValue() {
		assertTrue(activeCard.increaseDieValue());
		assertFalse(inactiveCard.increaseDieValue());
	}

	@Test
	public void testDecreaseDieValue() {
		assertTrue(activeCard.decreaseDieValue());
		assertFalse(inactiveCard.decreaseDieValue());
	}

	@Test
	public void testConsumeEffect1() {
		assertTrue(activeCard.increaseDieValue());
		assertTrue(activeCard.decreaseDieValue());
		try {
			activeCard.consumeEffect();
		} catch (ToolCardStateException e) {

		}
		assertFalse(activeCard.increaseDieValue());
		assertFalse(activeCard.decreaseDieValue());
	}

	@Test(expected = ToolCardStateException.class)
	public void testConsumeEffect2() throws ToolCardStateException{
			inactiveCard.consumeEffect();
			fail();
	}

	@Test
	public void testCost() {
		assertTrue(inactiveCard.cost() == ToolCard.NEVER_USED_TOKEN_COST);
		assertTrue(activeCard.cost() == ToolCard.USED_TOKEN_COST);
		try {
			inactiveCard.activate();
		} catch (ToolCardStateException e) {

		}
		assertTrue(inactiveCard.cost() == ToolCard.USED_TOKEN_COST);
	}

	@Test
	public void testGetter() {
		assertTrue(activeCard.getName().equals(ToolCardOne.NAME));
		assertTrue(activeCard.getInfo().equals(ToolCardOne.INFO));
	}

	@Test
	public void testOtherMethods() {
		assertFalse(activeCard.chooseNewDieValue());
		assertFalse(activeCard.flipDraftedDie());
		assertFalse(activeCard.moveADie());
		assertFalse(activeCard.moveDieIgnoreColor());
		assertFalse(activeCard.moveDieIgnoreValue());
		assertFalse(activeCard.moveTwoDiceMatchColorOnRoundTrack());
		assertFalse(activeCard.placeDieAfterFirstTurn());
		assertFalse(activeCard.placeDraftedDieNoAdjacent());
		assertFalse(activeCard.rerollAllDraftPoolDice());
		assertFalse(activeCard.rerollDraftedDie());
		assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
		assertFalse(activeCard.swapDraftDieWithRoundTrackDie());
	}
}
