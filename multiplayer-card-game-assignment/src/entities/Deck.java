package entities;

import entities.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cardList;

    public Deck() {

        cardList = new ArrayList<>();

        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        for (int i = 0; i < ranks.length; i++) {

            for (int j = 0; j < suits.length; j++) {
                Card card = new Card(ranks[i], suits[j], values[i]);
                cardList.add(card);
            }

        }
    }


    /*------   Shuffling the deck   -------*/
    public void shuffle() {
        Collections.shuffle(cardList);
    }


    /*------   Drawing a card from the top   -------*/
    public Card drawGame() {
        if (cardList.isEmpty()) {
            return null;
        }
        return cardList.remove(0);
    }


}
