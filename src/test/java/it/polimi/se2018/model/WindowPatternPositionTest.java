package it.polimi.se2018.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WindowPatternPositionTest {

    @Test
    public void toInt() {
        assertEquals(WindowPatternPosition.FIRST.toInt(), 0);
        assertEquals(WindowPatternPosition.SECOND.toInt(), 1);
        assertEquals(WindowPatternPosition.THIRD.toInt(), 2);
        assertEquals(WindowPatternPosition.FOURTH.toInt(), 3);
    }

    @Test
    public void fromInt() {
        assertEquals(WindowPatternPosition.FIRST, WindowPatternPosition.fromInt(0));
        assertEquals(WindowPatternPosition.SECOND, WindowPatternPosition.fromInt(1));
        assertEquals(WindowPatternPosition.THIRD, WindowPatternPosition.fromInt(2));
        assertEquals(WindowPatternPosition.FOURTH, WindowPatternPosition.fromInt(3));
    }

}