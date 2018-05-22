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
    RoundTrack roundTrack,roundTrack1;
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
    }
    @Test
    public void addDiceTest(){
        ArrayList<Die> tempDice= new ArrayList<>();
        Die tempDie= new Die(DieColor.getRandom(),DieValue.getRandom());
        Die tempDie1=new Die(DieColor.getRandom(),DieValue.getRandom());
        tempDice.add(tempDie);
        tempDice.add(tempDie1);
        System.out.println(roundTrack.getRound());
        System.out.println(tempDice.get(0));
        roundTrack.addDice(tempDice);
        try {
            roundTrack.addDie(tempDie);
            fail();
        } catch (NotValidRoundException e) {
            e.printStackTrace();
        }
        try {
            roundTrack.addDie(tempDie,1);
            fail();
        } catch (NotValidRoundException e) {
            e.printStackTrace();
        }
        assertTrue(roundTrack.hasDie(tempDie));
        try {
            assertEquals(roundTrack.getAllDice(),roundTrack.getDice(roundTrack.getRound()));
            fail();
        } catch (RoundTrackEmptyException | NotValidRoundException e) {
            e.printStackTrace();
        }
        try {
            roundTrack.swapDice(tempDie,tempDie1,roundTrack.getRound());
            fail();
        } catch (NotValidDieException | NotValidRoundException e) {
            e.printStackTrace();
        }
        tempDice.add(tempDie);
        try {
            assertEquals(tempDice,roundTrack.getDice(roundTrack.getRound()));
            fail();
        } catch (RoundTrackEmptyException | NotValidRoundException e) {
            e.printStackTrace();
        }
        roundTrack1= roundTrack.cloneRoundTrack();
        assertEquals(roundTrack,roundTrack1);
        roundTrack.remove(tempDie,roundTrack.getRound());
        roundTrack.remove(tempDie,roundTrack.getRound());
        assertTrue(roundTrack.isEmpty());
    }
}
