package it.polimi.se2018.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieValueTest {

    @Test
    public void toInt() {
        int temp = (int) Math.random() * 5;
        temp++;
        DieValue dieValue = DieValue.fromInt(temp);
        assertEquals(temp, dieValue.toInt());
    }
    @Test
    public void fromInt(){
        assertNull(DieValue.fromInt(7));

    }

    @Test
    public void oppositeValue(){
        assertTrue(DieValue.ONE == DieValue.SIX.oppositeValue());
        assertTrue(DieValue.TWO == DieValue.FIVE.oppositeValue());
        assertTrue(DieValue.THREE == DieValue.FOUR.oppositeValue());
        assertTrue(DieValue.FOUR == DieValue.THREE.oppositeValue());
        assertTrue(DieValue.FIVE == DieValue.TWO.oppositeValue());
        assertTrue(DieValue.SIX == DieValue.ONE.oppositeValue());
        assertFalse(DieValue.ONE == DieValue.ONE.oppositeValue());
    }
}