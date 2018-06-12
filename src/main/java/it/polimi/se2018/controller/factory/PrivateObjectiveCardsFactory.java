package it.polimi.se2018.controller.factory;

import it.polimi.se2018.model.DieColor;
import it.polimi.se2018.model.PrivateObjectiveCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrivateObjectiveCardsFactory {

    /**
     * Private objective cards.
     */
    private List<PrivateObjectiveCard> cards;

    /**
     * Constructor.
     * It initialize the factory.
     */
    public PrivateObjectiveCardsFactory() {
        cards = new ArrayList<>();
        cards.add(new PrivateObjectiveCard(DieColor.RED));
        cards.add(new PrivateObjectiveCard(DieColor.BLUE));
        cards.add(new PrivateObjectiveCard(DieColor.YELLOW));
        cards.add(new PrivateObjectiveCard(DieColor.GREEN));
        cards.add(new PrivateObjectiveCard(DieColor.PURPLE));
    }

    /**
     * Get a private objective card. Remove the card from the factory.
     * If this method will be called multiple times, it will never return the same card.
     */
    public PrivateObjectiveCard drawCard() {
        return cards.remove((int) Math.random() * cards.size());
    }

}
