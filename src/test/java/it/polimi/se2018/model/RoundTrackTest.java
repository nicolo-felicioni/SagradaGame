package it.polimi.se2018.model;

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
    RoundTrack roundTrack;
    @Before
    public void setup(){
        roundTrack= new RoundTrack();
    }
    @After
    public void tearDown(){
    }

    @Test
    public void addDiceTest(){
        ArrayList<Die> Dice= new ArrayList<>();
        DieColor tempColor= DieColor.getRandom();
        DieValue temValue= DieValue.getRandom();
        Dice.add(new Die(tempColor,temValue));
        Dice.add(new Die(DieColor.getRandom(),DieValue.getRandom()));
        Dice.add(new Die(DieColor.getRandom(),DieValue.getRandom()));
        roundTrack.addDice(Dice);
        System.out.println(roundTrack.getRound());
        try {
            assertEquals(Dice,roundTrack.getAllDice());
            fail();
        } catch (RoundTrackEmptyException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(Dice,roundTrack.getDice(roundTrack.getRound()));
            fail();
        } catch (RoundTrackEmptyException e) {
            e.printStackTrace();
        } catch (NotValidRoundException e) {
            e.printStackTrace();
        }
    }
}