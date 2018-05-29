package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidDieException;
import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author PeiQing Gao
 */
public class RoundTrackTest {
    private RoundTrack roundTrack;
    @Before
    public void setup(){
        roundTrack= new RoundTrack();
    }
    @After
    public void tearDown(){
    }

    @Test
    public void isEmptyTest(){
        assertTrue(roundTrack.isEmpty());
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        try {
            roundTrack.addDie(tempDie,roundTrack.getRound());
        } catch (NotValidRoundException e) {
            fail();
        }
        assertFalse(roundTrack.isEmpty());
    }
    @Test
    public void getRoundTest(){
        int i;
        assertTrue(roundTrack.getRound()==1);
        for (i=1;i<Math.random()*10;i++){
            Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
            try {
                roundTrack.addDie(tempDie,roundTrack.getRound());
            } catch (NotValidRoundException e) {
                fail();
            }
        }
        assertTrue(roundTrack.getRound()==i);
    }
    @Test
    public void addDieTest(){
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie1= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie2= new Die(DieColor.getRandom(),DieValue.getRandom());
        ArrayList<Die> tempDice= new ArrayList<>();
        try {
            roundTrack.addDie(tempDie,roundTrack.getRound());
        } catch (NotValidRoundException e) {
            fail();
        }
        assertTrue(roundTrack.hasDie(tempDie));
        try {
            roundTrack.addDie(tempDie,roundTrack.getRound()+1);
            fail();
        } catch (NotValidRoundException ignored) {
        }
        try {
            roundTrack.addDie(tempDie,0);
            fail();
        } catch (NotValidRoundException ignored) {
        }
        try {
            roundTrack.addDie(tempDie1,1);
        } catch (NotValidRoundException e) {
            fail();
        }
        assertTrue(roundTrack.hasDie(tempDie1));
        try {
            roundTrack.addDie(tempDie2);
        } catch (NotValidRoundException e) {
            fail();
        }
        assertTrue(roundTrack.hasDie(tempDie2));
    }
    @Test
    public void addDiceTest(){
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie1= new Die(DieColor.getRandom(),DieValue.getRandom());
        ArrayList<Die> tempDice= new ArrayList<>();
        ArrayList<Die> tempDice1= new ArrayList<>();
        tempDice.add(tempDie);
        tempDice.add(tempDie1);
        try {
            roundTrack.addDice(tempDice,roundTrack.getRound());
        } catch (NotValidRoundException e) {
            fail();
        }
        assertTrue(roundTrack.hasDie(tempDie));
        assertTrue(roundTrack.hasDie(tempDie1));
        try {
            roundTrack.addDice(tempDice,roundTrack.getRound()+1);
            fail();
        } catch (NotValidRoundException ignored) {
        }
        try {
            roundTrack.addDice(tempDice,0);
            fail();
        } catch (NotValidRoundException ignored) {
        }

    }
    @Test
    public void getAllDiceTest(){
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie1= new Die(DieColor.getRandom(),DieValue.getRandom());
        ArrayList<Die> tempDice= new ArrayList<>();
        tempDice.add(tempDie);
        tempDice.add(tempDie1);
        try {
            roundTrack.getAllDice();
            fail();
        } catch (RoundTrackEmptyException ignored) {
        }
        try {
            roundTrack.addDice(tempDice);
        } catch (NotValidRoundException e) {
            fail();
        }

    }
    @Test
    public void cloneRoundTrackTest(){
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie1= new Die(DieColor.getRandom(),DieValue.getRandom());
        ArrayList<Die> tempDice= new ArrayList<>();
        ArrayList<Die> tempDice1= new ArrayList<>();
        ArrayList<Die> tempDice2= new ArrayList<>();
        tempDice.add(tempDie);
        tempDice.add(tempDie1);
        RoundTrack tempRoundTrack=new RoundTrack();
        try {
            roundTrack.addDice(tempDice);
        } catch (NotValidRoundException e) {
            fail();
        }
        tempRoundTrack=roundTrack.cloneRoundTrack();
        assertNotEquals(roundTrack,tempRoundTrack);
        try {
            roundTrack.addDie(tempDie);
        } catch (NotValidRoundException e) {
            fail();
        }
        try {
            tempDice1=roundTrack.getAllDice();
            tempDice2=tempRoundTrack.getAllDice();
        } catch (RoundTrackEmptyException e) {
            e.printStackTrace();
        }
            for (int i=0;i<2;i++){
                tempDice1.get(i).equalsDie(tempDice2.get(i));
            }
    }
    @Test
    public void swapDiceTest(){
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie1= new Die(DieColor.getRandom(),DieValue.getRandom());
        ArrayList<Die> tempDice= new ArrayList<>();
        tempDice.add(tempDie);
        tempDice.add(tempDie1);
        try {
            roundTrack.addDice(tempDice);
        } catch (NotValidRoundException e) {
            fail();
        }
        try {
            roundTrack.swapDice(tempDie,tempDie1,0);
            fail();
        } catch (NotValidDieException | NotValidRoundException ignored) {

        }
        try {
            roundTrack.swapDice(tempDie,tempDie1,11);
            fail();
        } catch (NotValidDieException | NotValidRoundException ignored) {
        }
        try {
            assertTrue(roundTrack.swapDice(tempDie,tempDie1,1));
        } catch (NotValidDieException | NotValidRoundException e) {
            fail();
        }
        assertFalse(roundTrack.hasDie(tempDie1));
        try {
            tempDice=roundTrack.getAllDice();
        } catch (RoundTrackEmptyException e) {
            fail();
        }
        tempDice.get(0).equalsDie(tempDice.get(1));
    }
    @Test
    public void sortRoundTrackTest(){
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie1= new Die(DieColor.getRandom(),DieValue.getRandom());
        ArrayList<Die> tempDice= new ArrayList<>();
        tempDice.add(tempDie);
        tempDice.add(tempDie1);
        try {
            roundTrack.addDice(tempDice);
        } catch (NotValidRoundException e) {
            fail();
        }
        try {
            roundTrack.addDice(tempDice,1);
        } catch (NotValidRoundException e) {
            fail();
        }
        try {
            roundTrack.addDice(tempDice,1);
        } catch (NotValidRoundException e) {
            fail();
        }
            roundTrack.sortRoundTrack();
    }
}
