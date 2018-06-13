package it.polimi.se2018.model;



import it.polimi.se2018.exceptions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 @author Nicolò Felicioni
 */


public class WindowPatternTest {

    private WindowPattern window;
    private WindowPattern blankWindow;


    private int randomDiff;


    @Before
    public void setUp() throws Exception {

        Space[][] spaces;
        Space[][] blankSpaces;


        randomDiff = WindowPattern.MIN_DIFFICULTY + (int) (Math.random() * (WindowPattern.MAX_DIFFICULTY - WindowPattern.MIN_DIFFICULTY));

        int i=0;


        spaces = new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];
        blankSpaces = new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];

        //first row : blank spaces
        for(int j=0; j<WindowPattern.SPACES_LENGTH; j++)
            spaces[i][j] = new BlankSpace();

        i++;

        //second row : random colored spaces
        for(int j=0; j<WindowPattern.SPACES_LENGTH; j++){
            spaces[i][j] = new ColorSpace(DieColor.getRandom());
        }

        i++;

        // third row : random colored spaces

        for(int j=0; j<WindowPattern.SPACES_LENGTH; j++){
            spaces[i][j] = new ColorSpace(DieColor.getRandom());
        }

        i++;
        //fourth row : random value spaces
        for(int j=0; j<WindowPattern.SPACES_LENGTH; j++)
            spaces[i][j] = new ValueSpace(DieValue.getRandom());

        window = new WindowPattern(spaces, randomDiff);

        for(int k = 0; k<WindowPattern.SPACES_HEIGHT; k++)
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++)
                blankSpaces[k][j] = new BlankSpace();

        blankWindow = new WindowPattern(blankSpaces, randomDiff);
    }

    @After
    public void tearDown(){

        window = null;

    }

    @Test
    public void wrongCreationLength(){
        Space[][] wrongMatrix =
                new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH + 1];
        try {
            new WindowPattern(wrongMatrix, randomDiff);
            fail();
        } catch (WindowPatternDimensionException e) {
            System.out.println("ok");
        } catch (UnboundDifficultyValueException e) {
            fail();
        }

    }

    @Test
    public void wrongCreationHeight(){
        Space[][] wrongMatrix =
                new Space[WindowPattern.SPACES_HEIGHT + 1][WindowPattern.SPACES_LENGTH];
        try {
            WindowPattern wrongWindow = new WindowPattern(wrongMatrix, randomDiff);
            fail();
        } catch (WindowPatternDimensionException e) {
            System.out.println("ok");
        } catch (UnboundDifficultyValueException e) {
            fail();
        }

    }

    @Test
    public void wrongCreationDiff(){
        Space[][] okMatrix =
                new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];
        try {
            WindowPattern wrongWindow = new WindowPattern(okMatrix, randomDiff + 60);
            fail();
        } catch (WindowPatternDimensionException e) {
            fail();
        } catch (UnboundDifficultyValueException e) {

            System.out.println("ok");
        }


        try {
            WindowPattern wrongWindow = new WindowPattern(okMatrix, 0);
            fail();
        } catch (WindowPatternDimensionException e) {
            fail();
        } catch (UnboundDifficultyValueException e) {

            System.out.println("ok");
        }

    }

    @Test
    public void getDifficulty() {
        assertEquals(randomDiff, window.getDifficulty());
    }

    @Test
    public void getSpace() throws NotValidPointException {
        int x = 0, y ;
        Point p;

        for(y=0; y < WindowPattern.SPACES_LENGTH; y++){
            p = new Point(x, y);
            assertFalse(window.getSpace(p).isValueRestricted() || window.getSpace(p).isColorRestricted());
        }

        for(x = 1; x < 3; x++)
            for(y=0; y < WindowPattern.SPACES_LENGTH; y++){
                p = new Point(x, y);
                assertTrue(! window.getSpace(p).isValueRestricted() && window.getSpace(p).isColorRestricted());
            }

        for(y=0; y < WindowPattern.SPACES_LENGTH; y++){
            p = new Point(x, y);
            assertTrue(! window.getSpace(p).isColorRestricted() && window.getSpace(p).isValueRestricted());
        }

    }

    @Test
    public void getSpace1() {
        int x = 0, y ;

        for(y=0; y < WindowPattern.SPACES_LENGTH; y++){
            try {
                assertFalse(window.getSpace(x, y).isValueRestricted() || window.getSpace(x, y).isColorRestricted());
            } catch (NotValidPointException e) {
                fail();
            }
        }

        for(x = 1; x < 3; x++)
            for(y=0; y < WindowPattern.SPACES_LENGTH; y++){

                try {
                    assertTrue(! window.getSpace(x, y).isValueRestricted() && window.getSpace(x, y).isColorRestricted());
                } catch (NotValidPointException e) {
                    fail();
                }
            }

        for(y=0; y < WindowPattern.SPACES_LENGTH; y++){
            try {
                assertTrue(! window.getSpace(x, y).isColorRestricted() && window.getSpace(x, y).isValueRestricted());
            } catch (NotValidPointException e) {
                fail();
            }
        }
    }

    @Test
    public void getAllSpaces() {
        Space[][] copy = window.getAllSpaces();
        for(int i = 0; i<WindowPattern.SPACES_HEIGHT; i++)
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++) {
                try {
                    assertTrue(copy[i][j].equalsSpace(window.getSpace(i, j)));
                } catch (NotValidPointException e) {
                    fail();
                }
            }
    }

    @Test
    public void placeDieRandom() throws NotValidPointException {

        int randX = (int) (Math.random() * WindowPattern.SPACES_HEIGHT);
        int randY = (int) (Math.random() * WindowPattern.SPACES_LENGTH);
        Die die = new Die(DieColor.getRandom(), DieValue.getRandom());
        Point p = new Point(randX, randY);

        try {
            window.placeDie(die, p);

            System.out.println("Die placed.");

            if(window.getSpace(p).isValueRestricted())
                assertTrue(die.getValue()==window.getSpace(p).getValueRestriction());

            if(window.getSpace(p).isColorRestricted())
                assertTrue(die.getColor()==window.getSpace(p).getColorRestriction());

            assertTrue(p.isEdgyPoint());


        } catch (PlacementException e) {

            System.out.println(e);

            if(die.getValue()==window.getSpace(p).getValueRestriction() || die.getColor()==window.getSpace(p).getColorRestriction())
                assertFalse(p.isEdgyPoint());
            else if(window.getSpace(p).isValueRestricted())
                assertFalse(die.getValue()==window.getSpace(p).getValueRestriction());
            else if(window.getSpace(p).isColorRestricted())
                assertFalse(die.getColor()==window.getSpace(p).getColorRestriction());

        }finally {
            System.out.println("Point :" + p );
            System.out.println("Space color :" + window.getSpace(p).getColorRestriction() );
            System.out.println("Space value :" + window.getSpace(p).getValueRestriction() );
            System.out.println("Die :" + die );

            if(window.getSpace(p).hasDie())
                try{
                    window.placeDie(die, p);
                    fail();
                }catch (PlacementException e){
                    System.out.println("Tried to place another die in the same point.");
                    System.out.println(e);
                }
        }
    }


    @Test
    public void placeDieIgnoreColorRandom() throws NotValidPointException {
        int randX = (int) (Math.random() * WindowPattern.SPACES_HEIGHT);
        int randY = (int) (Math.random() * WindowPattern.SPACES_LENGTH);
        Die die = new Die(DieColor.getRandom(), DieValue.getRandom());
        Point p = new Point(randX, randY);

        try {
            window.placeDieIgnoreColor(die, p);

            System.out.println("Die placed.");

            if(window.getSpace(p).isValueRestricted())
                assertTrue(die.getValue()==window.getSpace(p).getValueRestriction());


            assertTrue(p.isEdgyPoint());


        } catch (PlacementException e) {

            System.out.println(e);

            if(die.getValue()==window.getSpace(p).getValueRestriction())
                assertFalse(p.isEdgyPoint());
            else if(window.getSpace(p).isValueRestricted())
                assertFalse(die.getValue()==window.getSpace(p).getValueRestriction());



        }finally {
            System.out.println("Point :" + p );
            System.out.println("Space color :" + window.getSpace(p).getColorRestriction() );
            System.out.println("Space value :" + window.getSpace(p).getValueRestriction() );
            System.out.println("Die :" + die );

            if(window.getSpace(p).hasDie())
                try{
                    window.placeDieIgnoreColor(die, p);
                    fail();
                }catch (PlacementException e){
                    System.out.println("Tried to place another die in the same point.");
                    System.out.println(e);
                }
        }
    }

    @Test
    public void placeDieIgnoreValueRandom() throws NotValidPointException {
        int randX = (int) (Math.random() * WindowPattern.SPACES_HEIGHT);
        int randY = (int) (Math.random() * WindowPattern.SPACES_LENGTH);
        Die die = new Die(DieColor.getRandom(), DieValue.getRandom());
        Point p = new Point(randX, randY);

        try {
            window.placeDieIgnoreValue(die, p);

            System.out.println("Die placed.");

            if(window.getSpace(p).isColorRestricted())
                assertTrue(die.getColor()==window.getSpace(p).getColorRestriction());


            assertTrue(p.isEdgyPoint());


        } catch (PlacementException e) {

            System.out.println(e);

            if(die.getColor()==window.getSpace(p).getColorRestriction())
                assertFalse(p.isEdgyPoint());
            else if(window.getSpace(p).isColorRestricted())
                assertFalse(die.getColor()==window.getSpace(p).getColorRestriction());


        }finally {
            System.out.println("Point :" + p );
            System.out.println("Space color :" + window.getSpace(p).getColorRestriction() );
            System.out.println("Space value :" + window.getSpace(p).getValueRestriction() );
            System.out.println("Die :" + die );

            if(window.getSpace(p).hasDie())
                try{
                    window.placeDieIgnoreValue(die, p);
                    fail();
                }catch (PlacementException e){
                    System.out.println("Tried to place another die in the same point.");
                    System.out.println(e);
                }
        }
    }

    @Test
    public void placeDieRandomHundredTimes() throws NotValidPointException {
        for(int i=0; i<100; i++){
            placeDieRandom();
            System.out.println(i);

            tearDown();

            try {
                setUp();
            } catch (Exception e) {
                System.out.println("eccezione di set up");
            }
        }

        for(int i=0; i<100; i++){
            placeDieIgnoreColorRandom();
            System.out.println(i);

            tearDown();

            try {
                setUp();
            } catch (Exception e) {
                System.out.println("eccezione di set up");
            }
        }

        for(int i=0; i<100; i++){
            placeDieIgnoreValueRandom();
            System.out.println(i);

            tearDown();

            try {
                setUp();
            } catch (Exception e) {
                System.out.println("eccezione di set up");
            }
        }


    }
    @Test
    public void placeDie1() throws NotValidPointException {
        int randX = (int) (Math.random() * WindowPattern.SPACES_HEIGHT);
        int randY = (int) (Math.random() * WindowPattern.SPACES_LENGTH);
        Die die = new Die(DieColor.getRandom(), DieValue.getRandom());

        try {
            window.placeDie(die, randX, randY);

            System.out.println("Die placed.");

            if(window.getSpace( randX, randY).isValueRestricted())
                assertTrue(die.getValue()==window.getSpace( randX, randY).getValueRestriction());

            if(window.getSpace( randX, randY).isColorRestricted())
                assertTrue(die.getColor()==window.getSpace( randX, randY).getColorRestriction());

            assertTrue(new Point(randX, randY).isEdgyPoint());


        } catch (PlacementException e) {

            System.out.println(e);

            if(die.getValue()==window.getSpace( randX, randY).getValueRestriction() || die.getColor()==window.getSpace( randX, randY).getColorRestriction())
                assertFalse(new Point( randX, randY).isEdgyPoint());
            else if(window.getSpace( randX, randY).isValueRestricted())
                assertFalse(die.getValue()==window.getSpace( randX, randY).getValueRestriction());
            else if(window.getSpace( randX, randY).isColorRestricted())
                assertFalse(die.getColor()==window.getSpace( randX, randY).getColorRestriction());

        }finally {
            System.out.println("Point :" +  randX + ", " + randY );
            System.out.println("Space color :" + window.getSpace( randX, randY).getColorRestriction() );
            System.out.println("Space value :" + window.getSpace( randX, randY).getValueRestriction() );
            System.out.println("Die :" + die );

            if(window.getSpace( randX, randY).hasDie())
                try{
                    window.placeDie(die,  randX, randY);
                    fail();
                }catch (PlacementException e){
                    System.out.println("Tried to place another die in the same point.");
                    System.out.println(e);
                }
        }
    }

    @Test
    public void placeDieIgnoreColor1() throws NotValidPointException {
        int randX = (int) (Math.random() * WindowPattern.SPACES_HEIGHT);
        int randY = (int) (Math.random() * WindowPattern.SPACES_LENGTH);
        Die die = new Die(DieColor.getRandom(), DieValue.getRandom());


        try {
            window.placeDieIgnoreColor(die, randX, randY);

            System.out.println("Die placed.");

            if(window.getSpace(randX, randY).isValueRestricted())
                assertTrue(die.getValue()==window.getSpace(randX, randY).getValueRestriction());


            assertTrue(new Point(randX, randY).isEdgyPoint());


        } catch (PlacementException e) {

            System.out.println(e);

            if(die.getValue()==window.getSpace(randX, randY).getValueRestriction())
                assertFalse(new Point(randX, randY).isEdgyPoint());
            else if(window.getSpace(randX, randY).isValueRestricted())
                assertFalse(die.getValue()==window.getSpace(randX, randY).getValueRestriction());



        }finally {
            System.out.println("Point :" + randX + randY );
            System.out.println("Space color :" + window.getSpace(randX, randY).getColorRestriction() );
            System.out.println("Space value :" + window.getSpace(randX, randY).getValueRestriction() );
            System.out.println("Die :" + die );

            if(window.getSpace(randX, randY).hasDie())
                try{
                    window.placeDieIgnoreColor(die, randX, randY);
                    fail();
                }catch (PlacementException e){
                    System.out.println("Tried to place another die in the same point.");
                    System.out.println(e);
                }
        }
    }

    @Test
    public void placeDieIgnoreValue1() throws NotValidPointException {
        int randX = (int) (Math.random() * WindowPattern.SPACES_HEIGHT);
        int randY = (int) (Math.random() * WindowPattern.SPACES_LENGTH);
        Die die = new Die(DieColor.getRandom(), DieValue.getRandom());


        try {
            window.placeDieIgnoreValue(die, randX, randY);

            System.out.println("Die placed.");

            if(window.getSpace(randX, randY).isColorRestricted())
                assertTrue(die.getColor()==window.getSpace(randX, randY).getColorRestriction());


            assertTrue(new Point(randX, randY).isEdgyPoint());


        } catch (PlacementException e) {

            System.out.println(e);

            if(die.getColor()==window.getSpace(randX, randY).getColorRestriction())
                assertFalse(new Point(randX, randY).isEdgyPoint());
            else if(window.getSpace(randX, randY).isColorRestricted())
                assertFalse(die.getColor()==window.getSpace(randX, randY).getColorRestriction());



        }finally {
            System.out.println("Point :" + randX + randY );
            System.out.println("Space color :" + window.getSpace(randX, randY).getColorRestriction() );
            System.out.println("Space value :" + window.getSpace(randX, randY).getValueRestriction() );
            System.out.println("Die :" + die );

            if(window.getSpace(randX, randY).hasDie())
                try{
                    window.placeDieIgnoreValue(die, randX, randY);
                    fail();
                }catch (PlacementException e){
                    System.out.println("Tried to place another die in the same point.");
                    System.out.println(e);
                }
        }
    }


    //TODO - DA SPEZZARE IN PIU TEST UNITARI
    @Test
    public void moveDie() throws NotValidPointException, PlacementException {
        DieValue val = window.getSpace(3, 0).getValueRestriction();
        Die die = new Die(DieColor.getRandom(), val);
        Point a = new Point(0, 0);
        Point b = new Point(3, 0);
        try {
            window.placeDie(die, a);
        } catch (PlacementException e) {
            fail();
        }

        assertTrue(window.getSpace(a).hasDie());

        try {
            window.moveDie(a, b);
        } catch (NotValidMoveException e) {
            fail();
        }

        assertTrue(window.getSpace(b).hasDie());
        assertFalse(window.getSpace(a).hasDie());

        //-------------------------------------------


        Point x = new Point(0, 0);
        Point y = new Point(0, 1);

        try {
            blankWindow.moveDie(x, y);
            fail();
        } catch (NotValidMoveException e) {
        }


        Die first = new Die(DieColor.RED, DieValue.ONE);
        Die second = new Die(DieColor.PURPLE, DieValue.TWO);


        blankWindow.placeDie(first, x);
        blankWindow.placeDie(second, y );

        try {
            blankWindow.moveDie(x, y);
            fail();
        } catch (NotValidMoveException e) {
        }



    }


    @Test
    public void removeDie() throws NotValidPointException {
        try {
            window.removeDie(new Point(0, 0));
            fail();
        } catch (SpaceNotOccupiedException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getSpacesRow() {
        int k = 0;
        Space[] spacesRowK = window.getSpacesRow(k);
        assertTrue(Arrays.stream(spacesRowK).noneMatch(space -> space.isValueRestricted() ||
                space.isColorRestricted()));
    }

    @Test
    public void getSpacesColumn() {
        int k = 0;
        Space[] spacesColumnK = window.getSpacesColumn(k);


        assertTrue(spacesColumnK.length == WindowPattern.SPACES_HEIGHT);
        assertTrue(!spacesColumnK[0].isValueRestricted() && !spacesColumnK[0].isColorRestricted() );
        assertTrue(spacesColumnK[1].isColorRestricted() );
        assertTrue(spacesColumnK[2].isColorRestricted());
        assertTrue(spacesColumnK[3].isValueRestricted());

    }

    @Test
    public void getAllSpacesAsList() throws NotValidPointException {
        List<Space> list = window.getAllSpacesAsList();
        List<Space> myList = new ArrayList<>();
        Space temp;

        for(int i = 0; i<WindowPattern.SPACES_HEIGHT; i++){
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++) {
                myList.add(window.getSpace(i, j));
            }
        }

        for(int i=0; i<myList.size(); i++){
            temp=myList.get(i);
            for(int j=0; j<list.size(); j++)
                if(list.get(j).equalsSpace(temp))
                    list.remove(j);
        }

        assertTrue(list.isEmpty());




    }

    @Test
    public void getNumberOfDice() {
        assertEquals(0, window.getNumberOfDice());

        int count=0;

        for(int i = 0; i<WindowPattern.SPACES_HEIGHT; i++){
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++) {
                try {
                    window.placeDie(new Die(DieColor.getRandom(), DieValue.getRandom()), i, j);
                    count++;
                } catch (PlacementException e) {

                } catch (NotValidPointException e) {
                    fail();
                }
            }
        }
        assertEquals(count, window.getNumberOfDice());



    }

    @Test
    public void cloneWindowPattern() throws NotValidPointException {
        WindowPattern clone = window.cloneWindowPattern();
        for(int i = 0; i<WindowPattern.SPACES_HEIGHT; i++)
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++)
                assertTrue(clone.getSpace(i, j).equalsSpace(window.getSpace(i, j)));

    }

    //@Test
    //public void jsonTest() {
    //   WindowPattern window1 = new WindowPatternFactory().getWindowPattern();
    //    window = new WindowPatternFactory().getWindowPattern();
    //    String s = new WindowPatternFactory().toJson(window);
    //    System.out.println(s);
    //    s = new WindowPatternFactory().toJson(window1);
    //   System.out.println(s);
    //    WindowPattern temp = new WindowPatternFactory().fromJson(s);
    //    assertTrue(window.equalsWindowPattern(window1));
    //}
}