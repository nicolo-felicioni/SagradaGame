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
}