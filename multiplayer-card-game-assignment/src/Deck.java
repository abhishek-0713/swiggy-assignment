import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                Card card = new Card(ranks[i], suit, values[i]);
                cards.add(card);
            }
        }
    }


    public void addCard(Card card) {
        cards.add(card);
    }

}