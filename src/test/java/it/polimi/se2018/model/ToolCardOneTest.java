package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ToolCardStateException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author davide yi xian hu
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
	public void testConsumeEffect() {
		assertTrue(activeCard.increaseDieValue());
		assertTrue(activeCard.decreaseDieValue());
		try {
			activeCard.consumeEffect();
		} catch (ToolCardStateException e) {

		}
		assertFalse(activeCard.increaseDieValue());
		assertFalse(activeCard.decreaseDieValue());
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
