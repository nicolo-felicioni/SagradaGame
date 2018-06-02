package it.polimi.se2018.controller;

import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.View;

public class ToolCardUpdater implements ViewUpdaterInterface {

    private ToolCard toolCard;


    public ToolCardUpdater(ToolCard toolCard){
        this.toolCard = toolCard.cloneToolCard();
    }

    @Override
    public void update(View view) {
        view.updateToolCard(toolCard);
    }
}
