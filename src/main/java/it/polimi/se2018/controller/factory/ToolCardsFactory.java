package it.polimi.se2018.controller.factory;

import it.polimi.se2018.model.*;

import java.util.List;

public class ToolCardsFactory {
    /**
     * Tool cards.
     */
    private List<ToolCard> cards;

    /**
     * Constructor.
     * It initialize the factory.
     */
    public ToolCardsFactory() {
        this.cards.add(new ToolCardOne());
        this.cards.add(new ToolCardTwo());
        this.cards.add(new ToolCardThree());
        this.cards.add(new ToolCardFour());
        this.cards.add(new ToolCardFive());
        this.cards.add(new ToolCardSix());
        this.cards.add(new ToolCardSeven());
        this.cards.add(new ToolCardEight());
        this.cards.add(new ToolCardNine());
        this.cards.add(new ToolCardTen());
        this.cards.add(new ToolCardEleven());
        this.cards.add(new ToolCardTwelve());
    }

    /**
     * Get a tool card. Remove the card from the factory.
     * If this method will be called multiple times, it will never return the same card.
     */
    public ToolCard drawCard() {
        return cards.remove((int) Math.random() * cards.size());
    }

    /**
     * Get a set of private objective card. Remove the cards from the factory.
     * If this method will be called multiple times, it will never return the same cards.
     * @param n the number of cards to be drawn.
     * @return an array of private objective cards.
     */
    public ToolCard[] drawCard(int n) {
        ToolCard [] cards = new ToolCard[n];
        for(int i = 0 ; i < n; i++) {
            cards[i] = this.cards.remove((int) Math.random() * this.cards.size());
        }
        return cards;
    }
}
