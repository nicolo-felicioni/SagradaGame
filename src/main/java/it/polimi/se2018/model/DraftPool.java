package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.DraftPoolEmptyException;

import java.io.Serializable;
import java.util.ArrayList;

public class DraftPool implements Serializable {
    ArrayList<Die> draftPool;

    /**
     * Empty Constructor
     */
    public DraftPool(){
    }

    /**
     * Copy Constructor
     * @param draftpool
     */
    public DraftPool(ArrayList<Die> draftpool){
        this.draftPool=draftpool;
    }

    /**
     * Returns true if draftpool contains no elements.
     * @return true of false
     */
    public boolean isEmpty(){
        return draftPool.isEmpty();
    }

    /**
     * Return the size of the draftpool
     * @return true or false
     */
    public int size(){
        return draftPool.size();
    }
    public boolean hasDie(Die die){
        for(Die tempDie:draftPool){
            if(tempDie.equalsDie(tempDie))
                return true;
        }
        return false;
    }

    /**
     * Method for adding a Die in the draft pool
     * @param die
     */
    public void addDie(Die die){
        draftPool.add(new Die(die));
    }

    /**
     * Method for adding dice in the draft pool
     * @param dice
     */
    public void addDice(ArrayList<Die> dice){
        for (Die die:dice){
            draftPool.add(new Die(die));
        }
    }

    /**
     *Method for removing a die from the draft pool
     * @param die
     * @return true if this list contained the specified element
     */
    public boolean removeDie(Die die){
        for (Die tempDie:draftPool){
            if (tempDie.equalsDie(die))
                return draftPool.remove(tempDie);
        }
        return false;
    }

    /**
     * Method for get all the dice in the draft pool as an ArrayList
     * @return ArrayList<Die> containing all the dice in the draft pool
     * @throws DraftPoolEmptyException
     */
    public ArrayList<Die> getAllDice() throws DraftPoolEmptyException {
        ArrayList<Die> tempDraftPool= new ArrayList<>();
        if (isEmpty())
            throw new DraftPoolEmptyException("Draft pool is empty");
        else
            for (Die die:draftPool)
                tempDraftPool.add(new Die(die));
        return tempDraftPool;
    }
}
