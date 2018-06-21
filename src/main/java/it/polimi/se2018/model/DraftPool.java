package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.DraftPoolEmptyException;
import it.polimi.se2018.exceptions.NotValidDieException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DraftPool implements Serializable {

    private List<Die> draftPool;

    /**
     * Empty Constructor
     */
    public DraftPool(){
        draftPool = new ArrayList<>();
    }

    /**
     * Copy Constructor
     * @param draftPool
     */
    public DraftPool(DraftPool draftPool){
        this.draftPool= new ArrayList<>(draftPool.getAllDice());
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
        draftPool.add(die);
    }

    /**
     * Method for adding dice in the draft pool
     * @param dice
     */
    public void addDice(List<Die> dice){
       draftPool.addAll(dice);
    }

    /**
     *Method for removing a die from the draft pool
     * @param die
     * @throws NotValidDieException if the die is not present within the dice bag
     */
    public void removeDie(Die die) throws NotValidDieException{

       for(int i=0; i<draftPool.size(); i++){
           if(draftPool.get(i).equalsDie(die)){
               draftPool.remove(i);
               return;
           }
       }
       throw new NotValidDieException(die + "isn't in the dice bag");
    }

    /**
     * Method for get all the dice in the draft pool as an ArrayList
     * @return ArrayList<Die> containing all the dice in the draft pool
     */
    public List<Die> getAllDice() {
        return new ArrayList<>(draftPool);
    }

    /**
     * clone of this draft pool
     * @return a clone of this draft pool
     */
    public DraftPool cloneDraftPool(){
        return new DraftPool(this);
    }

    /**
     * Roll every dice in the draft pool.
     */
    public void rollAllDice() {
        this.draftPool.forEach(d -> d.roll());
    }

}
