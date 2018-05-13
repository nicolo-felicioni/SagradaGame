package it.polimi.se2018.model;

/**
 * @author Nicolò Felicioni
 */

import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.PlacementException;

public class YourTurn extends PlayerState {

    @Override
    public void placeDie(WindowPattern window, Point p, Die die) throws PlacementException {
        window.getSpace(p).placeDie(die);
    }

    @Override
    public void placeDie(WindowPattern window, int x, int y, Die die) throws NotValidPointException, PlacementException {

        window.getSpace(new Point(x, y)).placeDie(die);

    }

    @Override
    public void useTool(ToolCard card) throws PlacementException {
        //TODO - DA FINIRE
        toolActivated = true;
    }

    @Override
    public void endTurn() {

    }
}