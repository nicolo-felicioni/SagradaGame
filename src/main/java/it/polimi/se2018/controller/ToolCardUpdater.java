package it.polimi.se2018.controller;

import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.View;

public class ToolCardUpdater implements ViewUpdaterInterface {

    private ToolCard toolCard;
    private int number;


    public ToolCardUpdater(ToolCard toolCard, int number){
        this.toolCard = toolCard.cloneToolCard();
        this.number=number;
    }

    @Override
    public void update(View view) {
        view.updateToolCard(toolCard, number);
    }
}
