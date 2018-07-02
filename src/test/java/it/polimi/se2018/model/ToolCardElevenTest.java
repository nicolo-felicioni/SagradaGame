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
public class ToolCardElevenTest {
    private ToolCardEleven activeCard;
    private ToolCardEleven inactiveCard;

    @Before
    public void setUp() {
        activeCard = new ToolCardEleven();
        inactiveCard = new ToolCardEleven();
        try {
            activeCard.activate();
        } catch (ToolCardStateException e) {

        }
    }

    @Test
    public void testReturnDie() {
        assertTrue(activeCard.returnDieAndGetNewFromDiceBag());
        assertFalse(inactiveCard.returnDieAndGetNewFromDiceBag());
    }

    @Test
    public void testChooseValue() {
        assertFalse(activeCard.chooseNewDieValue());
        assertFalse(inactiveCard.chooseNewDieValue());
    }

    @Test
    public void testConsumeEffect() {
        assertTrue(activeCard.returnDieAndGetNewFromDiceBag());
        assertTrue(activeCard.isActive());
        assertFalse(activeCard.chooseNewDieValue());
        try {
            activeCard.consumeEffect();
        } catch (ToolCardStateException e) {
            fail();
        }
        assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
        assertTrue(activeCard.isActive());
        assertTrue(activeCard.chooseNewDieValue());
        try {
            activeCard.consumeEffect();
        } catch (ToolCardStateException e) {
            fail();
        }
        assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
        assertFalse(activeCard.isActive());
        assertFalse(activeCard.chooseNewDieValue());
    }

    @Test(expected = ToolCardStateException.class)
    public void testConsumeEffect2() throws ToolCardStateException {
        inactiveCard.consumeEffect();
        fail();
    }

    @Test
    public void testEndActivation() {
        try {
            activeCard.endActivation();
            activeCard.activate();
            assertTrue(activeCard.returnDieAndGetNewFromDiceBag());
            assertTrue(activeCard.isActive());
            assertFalse(activeCard.chooseNewDieValue());
            try {
                activeCard.consumeEffect();
            } catch (ToolCardStateException e) {
                fail();
            }
            assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
            assertTrue(activeCard.isActive());
            assertTrue(activeCard.chooseNewDieValue());
            try {
                activeCard.consumeEffect();
            } catch (ToolCardStateException e) {
                fail();
            }
            assertFalse(activeCard.returnDieAndGetNewFromDiceBag());
            assertFalse(activeCard.isActive());
            assertFalse(activeCard.chooseNewDieValue());
        } catch (ToolCardStateException e) {
            fail();
        }

        try {
            inactiveCard.endActivation();
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
        assertFalse(activeCard.moveTwoDiceMatchColorOnRoundTrack());
        assertFalse(activeCard.placeDieAfterFirstTurn());
        assertFalse(activeCard.placeDraftedDieNoAdjacent());
        assertFalse(activeCard.rerollDraftedDie());
        assertFalse(activeCard.swapDraftDieWithRoundTrackDie());
        assertFalse(activeCard.rerollAllDraftPoolDice());
        assertFalse(activeCard.moveADie());
    }
}