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
public class ToolCardNineTest {
	private ToolCardNine activeCard;
	private ToolCardNine inactiveCard;

	@Before
	public void setUp() {
		activeCard = new ToolCardNine();
		inactiveCard = new ToolCardNine();
		try {
			activeCard.activate();
		} catch (ToolCardStateException e) {

		}
	}

	@Test
	public void testMoveDieIgnoreColor() {
		assertTrue(activeCard.placeDraftedDieNoAdjacent());
		assertFalse(inactiveCard.placeDraftedDieNoAdjacent());
	}

	@Test
	public void testConsumeEffect1() {
		assertTrue(activeCard.placeDraftedDieNoAdjacent());
		try {
			activeCard.consumeEffect();
		} catch (ToolCardStateException e) {

		}
		assertFalse(activeCard.placeDraftedDieNoAdjacent());
	}

	@Test(expected = ToolCardStateException.class)
	public void testConsumeEffect2() throws ToolCardStateException {
		inactiveCard.consumeEffect();
		fail();
	}

	@Test
	public void testOtherMethods() {
		assertFalse(activeCard.chooseNewDieValue());
		assertFalse(activeCard.flipDraftedDie());
		assertFalse(activeCard.moveDieIgnoreValue());
		assertFalse(activeCard.increaseDieValue());
		assertFalse(activeCard.decreaseDieValue());
		assertFalse(activeCard.moveDieIgnoreColor());
		assertFalse(activeCard.moveTwoDiceMatchColorOnRoundTrack());
		assertFalse(activeCard.swapDraftDieWithRoundTrackDie());
		assertFalse(activeCard.placeDieAfterFirstTurn());
		assertFalse(activeCard.rerollAllDraftPoolDice());
		assertFalse(activeCard.rerollDraftedDie());
		assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
		assertFalse(activeCard.moveADie());
	}
}