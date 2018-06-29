package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidDieException;
import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoundTrack implements Serializable {

    private List<Die>[] track;

    /**
     * Copy Constructor
     * @param track
     */
    public RoundTrack(List<Die>[] track){
        this.track=track;
    }

    /**
     * empty constructor
     */
    public RoundTrack(){
        track=new List[10];
        for (int i = 0; i < track.length; i++) {
            track[i] = new ArrayList();
        }

    }

    /**
     * return true if the round track is empty or false if round track is not empty
     * @return true or false
     */
    public boolean isEmpty(){
        return track[0].isEmpty();
    }
    /**
     *return the current turn number, from 1 to 10
     * @return the current turn number
     */
    public int getRound() {
        int i;
        for (i = 0; i < 10 && !track[i].isEmpty(); i++) {
        }
        return i + 1;
    }
    /**
     *return an List with all the dice on the round track
     * @return all the dice in the RoundTrack as a List
     * @throws RoundTrackEmptyException if the round track is empty
     */
    public List<Die> getAllDice () throws RoundTrackEmptyException {
        if (isEmpty())
            throw new RoundTrackEmptyException("Round Track is empty");
        else {
            ArrayList<Die> temp = new ArrayList<>();
            Die tempDie;

            for (int i=0;i<10&&!track[i].isEmpty();i++) {
                for (Die die : track[i]) {
                    tempDie = new Die(die);
                    temp.add(tempDie);
                }
            }
            return temp;
        }
    }

    /**
     * return all the dice in a specific round as a list
     *@return all the dice in a specific round as a list
     * @param round
     * @throws RoundTrackEmptyException if the round track is empty
     * @throws NotValidRoundException if the input is greater than 10 or lower than 0
     */
    public List<Die> getDice ( int round) throws RoundTrackEmptyException, NotValidRoundException {
        if (isEmpty())
            throw new RoundTrackEmptyException("Round Track is empty");
        if ((round < 1) || (round > getRound())) throw new NotValidRoundException("Not valid round number");
        else {
            ArrayList<Die> temp = new ArrayList<>();
            Die tempDie;
            List<Die> list = track[round-1];
            for (Die die : list) {
                tempDie = new Die(die);
                temp.add(tempDie);
            }
            return temp;
        }
    }

    /**
     * method for checking if the die is present on the round track
     * @param die
     * @return true or false
     */
    public boolean hasDie (Die die){
        for (int i = 0; (i < 10) && (!track[i].isEmpty()); i++) {
            for (Die tempdie : track[i]){
                if (tempdie.equalsDie(die)) return true;
            }
        }
        return false;
    }
    public void addDice(List<Die> dice,int round) throws NotValidRoundException {
        if ((round < 1) || (round > getRound()))
            throw new NotValidRoundException("Not valid round exception");
        for (Die die : dice) {
            track[round -1].add(new Die(die));
        }
        sortRoundTrack();
    }
    /**
     *method for adding an Arraylist of dice on the next round
     * @param dice
     *
     */
    public void addDice (List<Die> dice) {
        List<Die> d = new ArrayList<>();
        dice.forEach(die -> d.add(die.cloneDie()));
        track[getRound() -1].addAll(d);
        sortRoundTrack();
    }

    /**
     *method for adding a single die on a specific round of the round track
     * @param die
     * @param round
     */
    public void addDie (Die die,int round)throws NotValidRoundException {
        if ((round < 1) || (round > getRound()))
            throw new NotValidRoundException("Not valid round exception");
        track[round-1].add(new Die(die));
        sortRoundTrack();
    }

    /**
     * method for adding a single die on the next round of the round track
     * @param die
     * @throws NotValidRoundException
     */
    public void addDie (Die die) throws NotValidRoundException {
        addDie(die, getRound());
    }

    /**
     *method for removing a die from the specifc round
     * @param die
     * @param round
     * @return true or false
     */
    public boolean remove (Die die,int round) {
        for(Die tempdie:track[round]){
            if (tempdie.equalsDie(die)) {
                track[round].remove(tempdie);
                sortRoundTrack();
                return true;
            }
        }
        return false;
    }
    /**
     *method for swapping die, 1st Die is the added die and the 2nd die is the removed
     * @param addDie
     * @param removeDie
     * @param round
     * @return true or false
     */
    public boolean swapDice (Die addDie, Die removeDie,int round)throws NotValidDieException, NotValidRoundException
    {
        if ((round < 1) || (round > getRound()))
            throw new NotValidRoundException("Not valid round number");
        if (hasDie(removeDie)) {
            track[round-1].add(new Die(addDie));
            sortRoundTrack();
            return remove(removeDie,round-1);
        } else {
            throw new NotValidDieException("Not valid die");
        }
    }

    /**
     *
     * @return a copy the round track
     */
    public RoundTrack cloneRoundTrack () {
        return new RoundTrack(track);
    }

    /**
     * method for sorting the round track
     */
    public void sortRoundTrack(){
        for (int i=0;i<10&&!track[i].isEmpty();i++){
            track[i].sort(Die.DieComparator);
        }
    }
}