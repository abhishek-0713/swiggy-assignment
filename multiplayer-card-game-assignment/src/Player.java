import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String playerName;
    private final List<Card> cardsInHand;

    public Player(String playerName) {
        this.playerName = playerName;
        cardsInHand = new ArrayList<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }


    /*--------   Drawing a card   --------*/
    public void drawCard(Card card) {
        cardsInHand.add(card);
    }

   /*--------   Playing a card   ---------*/
    public Card playCard(int index) {
        if (index < 0 || index >= cardsInHand.size()) {
            return null;
        }
        return cardsInHand.remove(index);
    }


    /*-------   Checking if the player has any valid moves   --------*/
    public boolean isValidMove(Card topCard) {

        for (Card card : cardsInHand) {
            if (card.getPlayerRank().equals(topCard.getPlayerRank()) || card.getSuit().equals(topCard.getSuit())) {
                return true;
            }
        }
        return false;
    }
}
