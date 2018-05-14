package it.polimi.se2018.model;
/**
 * @author Nicolò Felicioni
 */


import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpaceTest {

    private Space blankSpace;
    private Space colorSpace;
    private Space valueSpace;
    private List<Space> spaces;
    private DieColor color;
    private DieValue value;

    @Before
    public void setUp() throws Exception {
        color = DieColor.getRandom();
        value = DieValue.getRandom();
        blankSpace = new BlankSpace();
        colorSpace = new ColorSpace(color);
        valueSpace = new ValueSpace(value);
        spaces = new ArrayList();
        spaces.add(colorSpace);
        spaces.add(valueSpace);
        spaces.add(blankSpace);
    }

    @After
    public void tearDown() throws Exception {
        spaces.clear();
    }


    //placeDie tests
    @Test
    public void placeDieGeneral() {
        DieColor tempCol = DieColor.getRandom();
        DieValue tempVal = DieValue.getRandom();

        try {
            colorSpace.placeDie(new Die(tempCol, tempVal));
            assertEquals(tempCol, colorSpace.getColorRestriction());

        } catch (PlacementException e) {
            assertNotEquals(tempCol, colorSpace.getColorRestriction());
        }

        try {
            valueSpace.placeDie(new Die(tempCol, tempVal));
            assertEquals(tempVal, valueSpace.getValueRestriction());

        } catch (PlacementException e) {
            assertNotEquals(tempVal, valueSpace.getValueRestriction());
        }

        try {
            blankSpace.placeDie(new Die(tempCol, tempVal));

        } catch (PlacementException e) {
            fail();
        }
    }

    @Test(expected = PlacementException.class)
    public void placeDieColorViolated() throws PlacementException {
        DieColor tempCol;

        do {
            tempCol = DieColor.getRandom();
        } while(tempCol == colorSpace.getColorRestriction());


        //expected exception here
        colorSpace.placeDie(new Die(tempCol, DieValue.getRandom()));


    }
    @Test(expected = PlacementException.class)
    public void placeDieValueViolated() throws PlacementException {
        DieValue tempVal;

        do {
            tempVal = DieValue.getRandom();
        } while(tempVal == valueSpace.getValueRestriction());


        //expected exception here
        valueSpace.placeDie(new Die(DieColor.getRandom(), tempVal));



    }



    @Test
    public void placeDieIgnoreColor() {
        DieColor tempCol;

        do {
            tempCol = DieColor.getRandom();
        } while(tempCol == colorSpace.getColorRestriction());


        try {
            colorSpace.placeDieIgnoreColor(new Die(tempCol, DieValue.getRandom()));
            assertTrue(colorSpace.hasDie());

        } catch (PlacementException e) {
            fail();
        }

    }

    @Test
    public void placeDieIgnoreValue() {
        DieValue tempVal;

        do {
            tempVal = DieValue.getRandom();
        } while(tempVal == valueSpace.getValueRestriction());


        try {
            valueSpace.placeDieIgnoreValue(new Die(DieColor.getRandom(), tempVal));
            assertTrue(valueSpace.hasDie());
        } catch (PlacementException e) {
            fail();
        }
    }

    @Test
    public void removeDie() {
        spaces.stream().forEach(space -> {
            try {
                space.placeDie(new Die(color, value));
            } catch (PlacementException e) {
                fail();
            }
        });
        System.out.println("inseriti dadi");

        assertTrue(spaces.stream().anyMatch(Space::hasDie));


        spaces.stream().forEach(space -> {
            try {
                space.removeDie();
            } catch (SpaceNotOccupiedException e) {
                fail();
            }
        });
        System.out.println("rimossi dadi");

        assertFalse(spaces.stream().anyMatch(Space::hasDie));



    }

    @Test
    public void respectAllRestrictions() {
        Die die1;
        Die die2;

        die1 = new Die(colorSpace.getColorRestriction(), valueSpace.getValueRestriction());

        do{
            die2 = new Die(DieColor.getRandom(), DieValue.getRandom());
        } while(die2.getColor()==colorSpace.getColorRestriction());

        while(die2.getValue() == valueSpace.getValueRestriction())
            die2.setValue(DieValue.getRandom());

        assertTrue(spaces.stream().allMatch(space -> space.respectAllRestrictions(die1)));

        assertFalse(colorSpace.respectAllRestrictions(die2));
        assertFalse(valueSpace.respectAllRestrictions(die2));



    }

    @Test
    public void isColorRestricted() {
        assertTrue(colorSpace.isColorRestricted());
        assertFalse(valueSpace.isColorRestricted() || blankSpace.isColorRestricted());
    }

    @Test
    public void isValueRestricted() {
        assertTrue(valueSpace.isValueRestricted());
        assertFalse(colorSpace.isValueRestricted() || blankSpace.isValueRestricted());
    }

    @Test
    public void getColorRestriction() {

        System.out.println("La restrizione di colore è " + colorSpace.getColorRestriction());
        System.out.println("Color è  " + color);

        assertEquals(colorSpace.getColorRestriction(), color);
        assertNull(blankSpace.getColorRestriction());
        assertNull(valueSpace.getColorRestriction());
    }

    @Test
    public void getValueRestriction() {
        System.out.println("La restrizione di valore è " + valueSpace.getValueRestriction());
        System.out.println("Value è  " + value);

        assertEquals(valueSpace.getValueRestriction(), value);
        assertNull(blankSpace.getValueRestriction());
        assertNull(colorSpace.getValueRestriction());


    }

    @Test
    public void hasDie() {
        assertTrue(spaces.stream().noneMatch(Space::hasDie));
    }
}