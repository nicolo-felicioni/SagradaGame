package it.polimi.se2018.model;

import java.util.ArrayList;

public class RoundTrack {

    private ArrayList<ArrayList<Die>> track;

    public ArrayList<Die> getAllDice() {
        ArrayList<Die> temp= new ArrayList<Die>();
        Die tempDie;

        for (ArrayList<Die> list : track) {
            for (Die die: list) {
                tempDie = new Die(die);
                temp.add(tempDie);
            }
        }
        return temp;
    }

    /**
     *
     * @param round
     */
    public ArrayList<Die> getDice(int round) {
        ArrayList<Die> temp= new ArrayList<Die>();
        Die tempDie;
        ArrayList<Die> list=track.get(round);
        for (Die die: list) {
            tempDie = new Die(die);
            temp.add(tempDie);
        }
        return temp;
    }

    public int getRound() {
        return track.size();
    }

    /**
     *
     * @param dice
     */
    public void addDice(ArrayList<Die> dice) {
        for (Die die:dice) {
            track.get(track.size()).add(new Die(die));
        }
    }

    /**
     *
     * @param addDie
     * @param removeDie
     * @param round
     */
    public boolean swapDice(Die addDie, Die removeDie, int round) {
        return track.get(round).add(new Die(addDie))&&track.get(round).remove(removeDie);
    }

    /**
     *
     * @param die
     * @param round
     */
    public void addDie(Die die, int round) {
        track.get(round).add(round,die);
    }

    /**
     *
     * @param die
     * @param round
     * @return
     */
    public boolean remove(Die die, int round) {
        return track.get(round).remove(die);
    }

    public ArrayList<ArrayList<Die>> getTrack() {
        return (ArrayList<ArrayList<Die>>) track.clone();
    }

}