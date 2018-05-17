package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.NotValidDieException;
import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;

import java.util.ArrayList;

public class RoundTrack {

    private ArrayList<ArrayList<Die>> track;

    /**
     * Copy Constructor
     * @param track
     */
    public RoundTrack(ArrayList<ArrayList<Die>> track){
        this.track=track;
    }

    /**
     * empty constructor
     */
    public RoundTrack(){
    }
    /**
     *
     * @return all the diece in the RoundTrack as an ArrayList of ArrayList
     * @throws RoundTrackEmptyException if the round track is empty
     */
    public ArrayList<Die> getAllDice() throws RoundTrackEmptyException {
        if (track.isEmpty())
            throw new RoundTrackEmptyException("Round Track is empty");
        else {
            ArrayList<Die> temp = new ArrayList<Die>();
            Die tempDie;

            for (ArrayList<Die> list : track) {
                for (Die die : list) {
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
    public ArrayList<Die> getDice(int round) throws RoundTrackEmptyException, NotValidRoundException {
        if (track.isEmpty())
            throw new RoundTrackEmptyException("Round Track is empty");
        if ((round<1)||(round>getRound())) throw new NotValidRoundException("Not valid round number");
        else {
            ArrayList<Die> temp = new ArrayList<Die>();
            Die tempDie;
            ArrayList<Die> list = track.get(round);
            for (Die die : list) {
                tempDie = new Die(die);
                temp.add(tempDie);
            }
            return temp;
        }
    }

    /**
     *
     * @return the current turn number
     */
    public int getRound() {
        return track.size();
    }

    /**
     * method for checking if the die is present on the round track
     * @param die
     * @return true or false
     */
    public boolean hasDie(Die die){
        for (ArrayList<Die> list : track) {
            if (list.contains(die))
                return true;
        }
        return false;
    }
    /**
     *method for adding an Arraylist of dice on the last round
     * @param dice
     *
     */
    public void addDice(ArrayList<Die> dice) {
        for (Die die:dice) {
            System.out.println(track.size());
            track.get(track.size()).add(new Die(die));
        }
    }

    /**
     *method for swapping die
     * @param addDie
     * @param removeDie
     * @param round
     * @return true or false
     */
    public boolean swapDice(Die addDie, Die removeDie, int round)throws NotValidDieException,NotValidRoundException {
        if ((round<1)||(round>getRound()))
            throw new NotValidRoundException("Not valid round number");
        if (hasDie(removeDie)) {
            return track.get(round).add(new Die(addDie)) && track.get(round).remove(removeDie);
        }
        else{
            throw new NotValidDieException("Not valid die");
        }
    }

    /**
     *method for adding a single die on a specific round of the round track
     * @param die
     * @param round
     */
    public void addDie(Die die, int round)throws NotValidRoundException {
        if ((round<1)||(round>getRound()))
            throw new NotValidRoundException("Not valid round exception");
        track.get(round).add(round,die);
    }

    /**
     * method for adding a single die on the last round of the round track
     * @param die
     * @throws NotValidRoundException
     */
    public void addDie(Die die) throws NotValidRoundException {
        addDie(die,track.size());
    }

    /**
     *method for removing an die in the round round
     * @param die
     * @param round
     * @return true or false
     */
    public boolean remove(Die die, int round) {
        if (track.get(round).contains(die))
            return track.get(round).remove(die);
        else
            return false;
    }

    /**
     *
     * @return a copy the round track
     */
    public ArrayList<ArrayList<Die>> getTrack() {
        return (ArrayList<ArrayList<Die>>) track.clone();
    }

}