package it.polimi.se2018.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Davide Yi Xian Hu
 */
public class CardPositionTest {

    @Test
    public void toInt() {
        assertEquals(CardPosition.LEFT.toInt(), 0);
        assertEquals(CardPosition.CENTER.toInt(), 1);
        assertEquals(CardPosition.RIGHT.toInt(), 2);
    }
    @Test
    public void fromInt(){
        assertEquals(CardPosition.LEFT, CardPosition.fromInt(0));
        assertEquals(CardPosition.CENTER, CardPosition.fromInt(1));
        assertEquals(CardPosition.RIGHT, CardPosition.fromInt(2));

    }
}