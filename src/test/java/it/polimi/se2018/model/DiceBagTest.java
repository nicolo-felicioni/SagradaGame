package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.DiceBagEmptyException;
import it.polimi.se2018.exceptions.DiceBagException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DiceBagTest {

    DiceBag diceBag;

    @Before
    public void setUp() throws Exception {
        diceBag = new DiceBag();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void drawDie() {
        int oldSize = diceBag.size();

        try {
            Die die = diceBag.drawDie();
        } catch (DiceBagException e) {
            fail();
        }

        assertEquals(oldSize, diceBag.size() + 1);

    }

    @Test
    public void drawDice() {

        List<Die> dice;
        int count;

        try {
            dice = diceBag.drawDice(90);

            for(int i = 0; i < 5; i++) {

                count=0;

                for(Die die : dice){
                    if(die.getColor( )== DieColor.fromInt(i))
                        count = count + 1;
                }
                assertEquals(count, 18);
            }
        } catch (DiceBagException e) {
            fail();
        }
        assertEquals(0 ,diceBag.size());

    }

    @Test
    public void size() {
        int random = (int) Math.random() * 90;
        int oldSize;

        oldSize = diceBag.size();
        try {
            diceBag.drawDice(random);
        } catch (DiceBagException e) {
            fail();
        }

        assertEquals(oldSize, diceBag.size() + random);

    }

    @Test
    public void isEmpty() {
        assertFalse(diceBag.isEmpty());
        try {
            diceBag.drawDice(90);
        } catch (DiceBagException e) {
            fail();
        }
        assertTrue(diceBag.isEmpty());

    }
}