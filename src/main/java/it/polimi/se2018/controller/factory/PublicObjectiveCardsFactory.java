package it.polimi.se2018.controller.factory;

import it.polimi.se2018.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Davide Yi Xian Hu
 */
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
        cards = new ArrayList<>();
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
        return cards.remove(new Random().nextInt(cards.size()));
    }

    /**
     * Get a set of private objective card. Remove the cards from the factory.
     * If this method will be called multiple times, it will never return the same cards.
     * @param n the number of cards to be drawn.
     * @return an array of private objective cards.
     */
    public PublicObjectiveCard[] drawCard(int n) {
        PublicObjectiveCard [] publicObjectiveCards = new PublicObjectiveCard[n];
        for(int i = 0 ; i < n; i++) {
            publicObjectiveCards[i] = cards.remove(new Random().nextInt(cards.size()));
        }
        return publicObjectiveCards;
    }

}
