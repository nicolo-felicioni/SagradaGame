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
public class ToolCardFiveTest {
	private ToolCardFive activeCard;
	private ToolCardFive inactiveCard;

	@Before
	public void setUp(){
		activeCard = new ToolCardFive();
		inactiveCard = new ToolCardFive();
		try {
			activeCard.activate();
		} catch (ToolCardStateException e) {

		}
	}

	@Test
	public void testMoveDieIgnoreColor() {
		assertTrue(activeCard.swapDraftDieWithRoundTrackDie());
		assertFalse(inactiveCard.swapDraftDieWithRoundTrackDie());
	}

	@Test
	public void testConsumeEffect1() {
		assertTrue(activeCard.swapDraftDieWithRoundTrackDie());
		try {
			activeCard.consumeEffect();
		} catch (ToolCardStateException e) {

		}
		assertFalse(activeCard.swapDraftDieWithRoundTrackDie());
	}

	@Test(expected = ToolCardStateException.class)
	public void testConsumeEffect2() throws ToolCardStateException{
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
		assertFalse(activeCard.placeDieAfterFirstTurn());
		assertFalse(activeCard.placeDraftedDieNoAdjacent());
		assertFalse(activeCard.rerollAllDraftPoolDice());
		assertFalse(activeCard.rerollDraftedDie());
		assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
		assertFalse(activeCard.moveADie());
	}
}
