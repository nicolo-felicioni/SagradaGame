package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidDieException;
import it.polimi.se2018.view.cli.Printer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

//TODO - CONTROLLARE TUTTE LE GENERAZIONI CASUALI DEI DADI NEI TEST

public class DraftPoolTest {

    DraftPool draftPool;

    @Before
    public void setUp() throws Exception {
        draftPool = new DraftPool();
    }

    @Test
    public void isEmpty() {
        assertTrue(draftPool.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(draftPool.size(), 0);

        List<Die> list = new ArrayList<>();
        list.add(Die.getRandomDie());
        list.add(Die.getRandomDie());
        list.add(Die.getRandomDie());

        draftPool.addDice(list);

        assertEquals(draftPool.size(), 3);
    }

    @Test
    public void hasDie() {
        Die random = Die.getRandomDie();
        assertFalse(draftPool.hasDie(random));

        draftPool.addDie(random);
        assertTrue(draftPool.hasDie(random));
    }

    @Test
    public void addDice() {
        List<Die> list = new ArrayList<>();
        list.add(Die.getRandomDie());
        list.add(Die.getRandomDie());
        list.add(Die.getRandomDie());

        draftPool.addDice(list);

        for(Die die : list)
            assertTrue(draftPool.hasDie(die));

    }

    @Test
    public void removeDieException() {
        try {
            draftPool.removeDie(Die.getRandomDie());
            fail();
        } catch (NotValidDieException e) {
            //ok
        }
    }

    @Test
    public void removeDie() {
        Die die = Die.getRandomDie();
        draftPool.addDie(die);
        assertTrue(draftPool.hasDie(die));


        try {
            draftPool.removeDie(die);
            assertFalse(draftPool.hasDie(die));
        } catch (NotValidDieException e) {
            fail();
        }
    }

    @Test
    public void getAllDice() {
        List<Die> dice = new ArrayList<>();
        dice.add(Die.getRandomDie());
        dice.add(Die.getRandomDie());
        dice.add(Die.getRandomDie());

        draftPool.addDice(dice);

        Printer.print(draftPool);

        List<Die> diceFromDraftPool = draftPool.getAllDice();

        assertEquals(dice.size(), diceFromDraftPool.size());

        for(Die die : dice){
            assertTrue(diceFromDraftPool.stream().anyMatch(d -> d.equalsDie(die)));
        }


    }
}