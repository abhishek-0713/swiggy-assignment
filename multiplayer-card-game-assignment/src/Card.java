import java.util.Objects;

public class Card {
    private final String playerRank;
    private final String suit;
    private final int value;

    public Card(String playerRank, String suit, int value) {
        this.playerRank = playerRank;
        this.suit = suit;
        this.value = value;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return getValue() == card.getValue() && Objects.equals(getPlayerRank(), card.getPlayerRank()) && Objects.equals(getSuit(), card.getSuit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerRank(), getSuit(), getValue());
    }

}
