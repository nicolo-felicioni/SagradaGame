package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidDieException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DraftPool implements Serializable {

    /**
     * the list of dice on the draft pool.
     */
    private List<Die> draft;


    /**
     * the drafted die.
     */
    private Die draftedDie;



    /**
     * Empty Constructor
     */
    public DraftPool(){
        draft = new ArrayList<>();
    }



    /**
     * Copy Constructor
     * @param draftPool
     */
    public DraftPool(DraftPool draftPool){
        this.draft= new ArrayList<>(draftPool.getAllDice());
        if(draftPool.draftedDie != null)
            this.draftedDie = new Die(draftPool.draftedDie);
    }

    /**
     * Returns true if draftpool contains no elements.
     * @return true of false
     */
    public boolean isEmpty(){
        return draft.isEmpty();
    }

    /**
     * Return the size of the draftpool
     * @return true or false
     */
    public int size(){
        return draft.size();
    }


    /**
     * returns true if the draftpool has the die
     * @param die the die to search in the draftpool
     * @return true if the die is present
     */
    public boolean hasDie(Die die){
        for(Die tempDie:draft){
            if(tempDie.equalsDie(tempDie))
                return true;
        }
        return false;
    }

    /**
     * Method for adding a die in the draft pool
     * @param die the die to add
     */
    public void addDie(Die die){
        draft.add(die.cloneDie());
    }

    /**
     * Method for adding dice in the draft pool
     * @param dice the dice to add
     */
    public void addDice(List<Die> dice){
       draft.addAll(dice);
    }

    /**
     *Method for removing a die from the draft pool
     * @param die the die to be removed
     * @throws NotValidDieException if the die is not present within the dice bag
     */
    public void removeDie(Die die) throws NotValidDieException{

       for(int i=0; i<draft.size(); i++){
           if(draft.get(i).equalsDie(die)){
               draft.remove(i);
               return;
           }
       }
       throw new NotValidDieException(die + "isn't in the draft pool.");
    }

    /**
     * Remove all dice from the draft pool.
     */
    public void removeAllDice() {
        draft.clear();
    }

    /**
     * Method for get all the dice in the draft pool as an ArrayList
     * @return a list of dice containing all the dice in the draft pool
     */
    public List<Die> getAllDice() {
        return new ArrayList<>(draft);
    }

    /**
     * clone of this draft pool
     * @return a clone of this draft pool
     */
    public DraftPool cloneDraftPool(){
        return new DraftPool(this);
    }

    /**
     * getter of the drafted die.
     * @return the drafted die.
     */
    public synchronized Die getDraftedDie() {
        if(this.draftedDie == null)
            return null;
        else
            return draftedDie.cloneDie();
    }

    /**
     * setter of the drafted die.
     * @param draftedDie the drafted die.
     */
    public synchronized void setDraftedDie(Die draftedDie) {
        this.draftedDie = draftedDie.cloneDie();
    }


    /**
     * Roll every dice in the draft pool.
     */
    public void rollAllDice() {
        this.draft.forEach(Die::roll);
    }

}
