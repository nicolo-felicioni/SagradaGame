package it.polimi.se2018.controller.factory;

import it.polimi.se2018.model.*;

import java.util.List;

public class PublicObjectiveCardsFactory {

    /**
     * Public objective cards.
     */
    private List<PublicObjectiveCard> cards;

    /**
     * Constructor.
     * It initialize the factory.
     */
    public PublicObjectiveCardsFactory() {
        this.cards.add(new PublicObjectiveCardOne());
        this.cards.add(new PublicObjectiveCardTwo());
        this.cards.add(new PublicObjectiveCardThree());
        this.cards.add(new PublicObjectiveCardFour());
        this.cards.add(new PublicObjectiveCardFive());
        this.cards.add(new PublicObjectiveCardSix());
        this.cards.add(new PublicObjectiveCardSeven());
        this.cards.add(new PublicObjectiveCardEight());
        this.cards.add(new PublicObjectiveCardNine());
        this.cards.add(new PublicObjectiveCardTen());
    }

    /**
     * Get a public objective card. Remove the card from the factory.
     * If this method will be called multiple times, it will never return the same card.
     */
    public PublicObjectiveCard drawCard() {
        return cards.remove((int) Math.random() * cards.size());
    }

    /**
     * Get a set of private objective card. Remove the cards from the factory.
     * If this method will be called multiple times, it will never return the same cards.
     * @param n the number of cards to be drawn.
     * @return an array of private objective cards.
     */
    public PublicObjectiveCard[] drawCard(int n) {
        PublicObjectiveCard [] cards = new PublicObjectiveCard[n];
        for(int i = 0 ; i < n; i++) {
            cards[i] = this.cards.remove((int) Math.random() * this.cards.size());
        }
        return cards;
    }

}
