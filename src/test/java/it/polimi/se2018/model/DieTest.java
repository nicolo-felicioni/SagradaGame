package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Nicolò Felicioni
 */


import static org.junit.Assert.*;

public class DieTest {


    @Test
    public void dieCreation(){
        DieValue tempVal = DieValue.getRandom();
        DieColor tempCol = DieColor.getRandom();
        System.out.println("tempVal is: " + tempVal);
        System.out.println("tempVal is: " + tempCol);


        Die tempDie = new Die(tempCol, tempVal);
        System.out.println("Die value is: " + tempDie.getValue());
        System.out.println("Die color is: " + tempDie.getColor());
        assertEquals(tempDie.getValue(), tempVal);
        assertEquals(tempDie.getColor(), tempCol);
    }
    @Test
    public void testCopyFactory(){
        Die die1= new Die(DieColor.RED,DieValue.FOUR);
        Die copy= new Die(die1);
        assertTrue(die1.equalsDie(copy));
        if(die1==copy) {
            fail();
        }
    }
    @Test
    public void equalsDie(){
        Die die1= new Die(DieColor.RED,DieValue.ONE);
        Die die2= new Die(die1);
        Die die3= new Die(DieColor.GREEN,DieValue.ONE);
        assertTrue(die1.equalsDie(die2));
        die2.setValue(DieValue.TWO);
        assertFalse(die1.equalsDie(die2));
        assertFalse(die1.equalsDie(die3));
        die3.setValue(DieValue.THREE);
        assertFalse(die1.equalsDie(die3));

    }
    @Test
    public void roll() {
        DieValue tempVal = DieValue.getRandom();
        DieColor tempCol = DieColor.getRandom();
        System.out.println("tempVal is: " + tempVal);
        System.out.println("tempVal is: " + tempCol);


        Die tempDie = new Die(tempCol, tempVal);


        System.out.println("Die value is: " + tempDie.getValue());
        System.out.println("Die color is: " + tempDie.getColor());

        DieColor oldColor = tempDie.getColor();

        tempDie.roll();

        System.out.println("Now die value is: " + tempDie.getValue());
        System.out.println("Now die color is: " + tempDie.getColor());

        assertEquals(oldColor, tempDie.getColor());



    }


}