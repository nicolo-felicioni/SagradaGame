package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ToolCardStateException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author davide yi xian hu
 */
public class ToolCardSevenTest {
	private ToolCardSeven activeCard;
	private ToolCardSeven inactiveCard;

	@Before
	public void setUp(){
		activeCard = new ToolCardSeven();
		inactiveCard = new ToolCardSeven();
		try {
			activeCard.activate();
		} catch (ToolCardStateException e) {

		}
	}

	@Test
	public void testMoveDieIgnoreColor() {
		assertTrue(activeCard.rerollAllDraftPoolDice());
		assertFalse(inactiveCard.rerollAllDraftPoolDice());
	}

	@Test
	public void testConsumeEffect() {
		assertTrue(activeCard.rerollAllDraftPoolDice());
		assertTrue(activeCard.isActive());
		try {
			activeCard.consumeEffect();
		} catch (ToolCardStateException e) {

		}
		assertFalse(activeCard.rerollAllDraftPoolDice());
		assertFalse(activeCard.isActive());
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
		assertFalse(activeCard.rerollDraftedDie());
		assertFalse(activeCard.swapDraftDieWithRoundTrackDie());
		assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
		assertFalse(activeCard.moveADie());
	}
}
