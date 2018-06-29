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
public class ToolCardTwelveTest {
    private ToolCardTwelve activeCard;
    private ToolCardTwelve inactiveCard;

    @Before
    public void setUp() {
        activeCard = new ToolCardTwelve();
        inactiveCard = new ToolCardTwelve();
        try {
            activeCard.activate();
        } catch (ToolCardStateException e) {

        }
    }

    @Test
    public void testMoveADieMatchColor() {
        assertTrue(activeCard.moveTwoDiceMatchColorOnRoundTrack());
        assertFalse(inactiveCard.moveTwoDiceMatchColorOnRoundTrack());
    }

    @Test
    public void testConsumeEffect() {
        assertTrue(activeCard.moveTwoDiceMatchColorOnRoundTrack());
        assertTrue(activeCard.isActive());
        try {
            activeCard.consumeEffect();
        } catch (ToolCardStateException e) {
            fail();
        }
        assertTrue(activeCard.moveTwoDiceMatchColorOnRoundTrack());
        assertTrue(activeCard.isActive());
        try {
            activeCard.consumeEffect();
        } catch (ToolCardStateException e) {
            fail();
        }
        assertFalse(activeCard.moveTwoDiceMatchColorOnRoundTrack());
        assertFalse(activeCard.isActive());
    }

    @Test(expected = ToolCardStateException.class)
    public void testConsumeEffect2() throws ToolCardStateException {
        inactiveCard.consumeEffect();
        fail();
    }

    @Test
    public void testEndActivation() {
        try {
            activeCard.endActivion();
            activeCard.activate();
            assertTrue(activeCard.moveTwoDiceMatchColorOnRoundTrack());
            assertTrue(activeCard.isActive());
            try {
                activeCard.consumeEffect();
            } catch (ToolCardStateException e) {
                fail();
            }
            assertTrue(activeCard.moveTwoDiceMatchColorOnRoundTrack());
            assertTrue(activeCard.isActive());
            try {
                activeCard.consumeEffect();
            } catch (ToolCardStateException e) {
                fail();
            }
            assertFalse(activeCard.moveTwoDiceMatchColorOnRoundTrack());
            assertFalse(activeCard.isActive());
        } catch (ToolCardStateException e) {
            fail();
        }
        try {
            inactiveCard.endActivion();
            fail();
        } catch (ToolCardStateException e) {

        }
    }

    @Test
    public void testOtherMethods() {
        assertFalse(activeCard.chooseNewDieValue());
        assertFalse(activeCard.flipDraftedDie());
        assertFalse(activeCard.moveDieIgnoreValue());
        assertFalse(activeCard.increaseDieValue());
        assertFalse(activeCard.decreaseDieValue());
        assertFalse(activeCard.moveDieIgnoreColor());
        assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
        assertFalse(activeCard.chooseNewDieValue());
        assertFalse(activeCard.placeDieAfterFirstTurn());
        assertFalse(activeCard.placeDraftedDieNoAdjacent());
        assertFalse(activeCard.rerollDraftedDie());
        assertFalse(activeCard.swapDraftDieWithRoundTrackDie());
        assertFalse(activeCard.rerollAllDraftPoolDice());
        assertFalse(activeCard.moveADie());
    }
}