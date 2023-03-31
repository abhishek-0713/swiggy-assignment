import entities.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();                        // Input Number of Players
        scanner.nextLine();

        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name of player " + i + ": ");
            String playerName = scanner.nextLine();                // Input entities.Player Name
            playerNames.add(playerName);
        }


//        Initialize and Start the entities.Game

        Game game = new Game();

        game.startGame(playerNames);
        game.playGame();
        game.endGame();

        scanner.close();
    }
}
