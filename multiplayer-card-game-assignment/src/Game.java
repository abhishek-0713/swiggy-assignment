import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private List<Player> playerList;
    private List<Card> drawPile;

//    private Player winner;
    private int currentPlayerIndex = 0;


    /*----------------    Starting the Game    ---------------*/
    public void startGame(List<String> playerNames) {

        // Initialize the deck and shuffle the cards
        deck = new Deck();
        deck.shuffle();

        // Create players
        playerList = new ArrayList<>();
        for (String playerName : playerNames) {
            playerList.add(new Player(playerName));
        }

        // Deal cards to each player
        int numCardsPerPlayer = 5;
        for (int i = 0; i < numCardsPerPlayer; i++) {
            for (Player player : playerList) {
                player.drawCard(deck.drawGame());
            }
        }

        // Draw the top card from the deck and place it on the discard pile
        drawPile = new ArrayList<>();
        drawPile.add(deck.drawGame());

        // Set the current player to the first player in the list
        currentPlayerIndex = 0;
    }



    /*----------------    Play The Game    ---------------*/
    public void playGame() {

        int currentPlayerIndex = 0;
        while (!isGameEnded()) {

            Player currentPlayer = playerList.get(currentPlayerIndex);
            Card topCard = drawPile.get(drawPile.size() - 1);

            System.out.println("Top card: " + topCard.getPlayerRank() + " of " + topCard.getSuit());
            System.out.println(currentPlayer.getPlayerName() + "'s turn:");

            if (!currentPlayer.isValidMove(topCard)) {
                System.out.println(currentPlayer.getPlayerName() + " has no valid moves.");
                currentPlayer.drawCard(deck.drawGame());
                continue;
            }
            int cardIndex = getPlayerCardIndex(currentPlayer, topCard);
            if (cardIndex == -1) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            Card playedCard = currentPlayer.playCard(cardIndex);
            drawPile.add(playedCard);

            System.out.println(currentPlayer.getPlayerName() + " played " + playedCard.getPlayerRank() + " of " + playedCard.getSuit());

            if (playedCard.getPlayerRank().equals("8")) {

                String suit = promptForSuit();
                playedCard = new Card("8", suit, 8);
                System.out.println(currentPlayer.getPlayerName() + " changed the suit to " + suit);
                drawPile.add(playedCard);
            }
            else if (playedCard.getPlayerRank().equals("2")) {

                System.out.println("Two more cards drawn");
                for (int i = 0; i < 2; i++) {
                    Player nextPlayer = getNextPlayer(currentPlayerIndex);
                    nextPlayer.drawCard(deck.drawGame());
                }
            }
            else if (playedCard.getPlayerRank().equals("Queen")) {
                System.out.println("Skip next player");
                currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
            }
            else if (playedCard.getPlayerRank().equals("Ace")) {
                System.out.println("Reverse direction");
                currentPlayerIndex = getPreviousPlayerIndex(currentPlayerIndex);
            }


            if (currentPlayer.getCardsInHand().isEmpty()) {
                System.out.println(currentPlayer.getPlayerName() + " wins!");
                break;
            }
            currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
        }

    }



    /*----------------    Get Player Card Index    ---------------*/
    private int getPlayerCardIndex(Player player, Card topCard) {

        List<Card> hand = player.getCardsInHand();
        System.out.println("Your hand:");

        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card.getPlayerRank().equals("8") || card.getPlayerRank().equals(topCard.getPlayerRank()) || card.getSuit().equals(topCard.getSuit())) {
                System.out.println(i + ": " + card.getPlayerRank() + " of " + card.getSuit());
            }
        }

        System.out.println("Enter the index of the card you want to play: ");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {

            int index = scanner.nextInt();
            if (index >= 0 && index < hand.size()) {

                Card card = hand.get(index);
                if (card.getPlayerRank().equals("8") || card.getPlayerRank().equals(topCard.getPlayerRank()) || card.getSuit().equals(topCard.getSuit())) {
                    return index;
                }
            }
        }
        return -1;
    }


    /*----------------    Choose a Suit    ---------------*/
    private String promptForSuit() {

        System.out.println("Choose a suit (Clubs, Diamonds, Hearts, Spades : ");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextLine()) {

            String suit = scanner.nextLine().toUpperCase();
            if (suit.equals("Clubs") || suit.equals("Diamonds") || suit.equals("Hearts") || suit.equals("Spades")) {
                return suit;
            }
        }
        System.out.println("Invalid input. Please try again.");
        return promptForSuit();
    }


    /*----------------    Get Next Player    ---------------*/
    private Player getNextPlayer(int currentPlayerIndex) {
        int nextPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
        return playerList.get(nextPlayerIndex);
    }


    /*----------------    Get Next Player Index    ---------------*/
    private int getNextPlayerIndex(int currentPlayerIndex) {

        int direction = 1;
        int nextPlayerIndex = currentPlayerIndex + direction;

        if (nextPlayerIndex < 0) {
            nextPlayerIndex = playerList.size() - 1;
        }
        else if (nextPlayerIndex >= playerList.size()) {
            nextPlayerIndex = 0;
        }
        return nextPlayerIndex;
    }


    /*----------------    Get Previous Player Index    ---------------*/
    private int getPreviousPlayerIndex(int currentPlayerIndex) {

        int direction = -1;
        int previousPlayerIndex = currentPlayerIndex - direction;

        if (previousPlayerIndex < 0) {
            previousPlayerIndex = playerList.size() - 1;
        }
        else if (previousPlayerIndex >= playerList.size()) {
            previousPlayerIndex = 0;
        }
        return previousPlayerIndex;
    }

    /*----------------    Is Game Ended     ---------------*/
    private boolean isGameEnded() {

        for (Player player : playerList) {
            if (player.getCardsInHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }



    /*----------------    Ending the Game    ---------------*/
    public void endGame() {

        Player winner = playerList.get(currentPlayerIndex);
        System.out.println("Congratulations, " + winner.getPlayerName() + " has won!");   // Print the winner

    }

}
