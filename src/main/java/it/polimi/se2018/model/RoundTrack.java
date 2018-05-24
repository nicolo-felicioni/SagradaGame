package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidDieException;
import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;

import java.util.ArrayList;
import java.util.List;

public class RoundTrack {

    private ArrayList<Die>[] track;

    /**
     * Copy Constructor
     * @param track
     */
    public RoundTrack(ArrayList<Die>[] track){
        this.track=track;
    }

    /**
     * empty constructor
     */
    public RoundTrack(){
        track=new ArrayList[10];
    }

    public boolean isEmpty(){
        return track[0] == null;
    }
    /**
     *
     * @return the current turn number
     */
    public int getRound() {
        int i=0;
        for (i = 0; i < 10 && track[i]!=null; i++) {
        }
        return i + 1;
    }
    /**
     *
     * @return all the dice in the RoundTrack as an ArrayList
     * @throws RoundTrackEmptyException if the round track is empty
     */
    public ArrayList<Die> getAllDice () throws RoundTrackEmptyException {
        if (isEmpty())
            throw new RoundTrackEmptyException("Round Track is empty");
        else {
            ArrayList<Die> temp = new ArrayList<>();
            Die tempDie;

            for (int i=0;i<10&&track[i]!=null;i++) {
                for (Die die : track[i]) {
                    tempDie = new Die(die);
                    temp.add(tempDie);
                }
            }
            return temp;
        }
    }

    /**
     *@return all the dice in a specific round as an arraylist
     * @param round
     * @throws RoundTrackEmptyException if the round track is empty
     * @throws NotValidRoundException if the input is greater than 10 or lower than 0
     */
    public ArrayList<Die> getDice ( int round) throws RoundTrackEmptyException, NotValidRoundException {
        if (isEmpty())
            throw new RoundTrackEmptyException("Round Track is empty");
        if ((round < 1) || (round > getRound())) throw new NotValidRoundException("Not valid round number");
        else {
            ArrayList<Die> temp = new ArrayList<>();
            Die tempDie;
            ArrayList<Die> list = track[round];
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
        for (int i=0;i<10&&track[i]!=null;i++) {
            if (track[i].contains(die))
                return true;
        }
        return false;
    }
    /**
     *method for adding an Arraylist of dice on the last round
     * @param dice
     *
     */
    public void addDice (ArrayList<Die> dice) {
        for (Die die : dice) {
            track[getRound()-1].add(new Die(die));
        }
    }

    /**
     *method for swapping die
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
            return track[getRound()-1].add(new Die(addDie)) && track[getRound()-1].remove(removeDie);
        } else {
            throw new NotValidDieException("Not valid die");
        }
    }

    /**
     *method for adding a single die on a specific round of the round track
     * @param die
     * @param round
     */
    public void addDie (Die die,int round)throws NotValidRoundException {
        if ((round < 1) || (round > getRound()))
            throw new NotValidRoundException("Not valid round exception");
        track[round-1].add(die);
    }

    /**
     * method for adding a single die on the last round of the round track
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
        return track[round].contains(die) && track[round].remove(die);
    }

    /**
     *
     * @return a copy the round track
     */
    public RoundTrack cloneRoundTrack () {
        return new RoundTrack(track);
    }
}