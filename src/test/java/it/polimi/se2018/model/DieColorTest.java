package it.polimi.se2018.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieColorTest {

    @Test
    public void colorFail(){
        assertNull(DieColor.fromInt(8));
    }

}