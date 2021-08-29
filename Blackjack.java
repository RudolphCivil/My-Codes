import java.util.Scanner;


public class Blackjack {
    public static int playValue(int playerNumber) { //This is for the value of the cards//
        if (playerNumber == 1) {
            System.out.println("Your card is a ACE!");
            return 1;
        }
        if (playerNumber < 11) {
            System.out.println("Your card is a " + playerNumber + "!");
            return playerNumber;
        }
        if (playerNumber == 11) {
            System.out.println("Your card is a JACK!");
            return 10;
        }
        if (playerNumber == 12) {
            System.out.println("Your card is a QUEEN!");
            return 10;
        }
        if (playerNumber == 13) {
            System.out.println("Your card is a KING!");
            return 10;
        }
        return 10;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        P1Random random = new P1Random();
        boolean newGame = true; //Important for loop//
        int gameNumber = 1;
        int playerNumber;
        int player;
        int dealer;
        int decision;
        int totalMatches = 0;
        int playerWins = 0;
        int gameTie = 0;
        int dealerWins = 0;
        int playerHandValue = 0;
        while (true) {
            if (newGame) {
                System.out.println("START GAME #" + gameNumber);
                playerNumber = random.nextInt(13) + 1;
                player = playerNumber;
                playerHandValue = playValue(player);

                System.out.println("Your hand is: " + playerHandValue);
                newGame = false;
            }
            System.out.println("1. Get another card"); // Start of the Menu//
            System.out.println("2. Hold hand");
            System.out.println("3. Print statistics");
            System.out.println("4. Exit");
            System.out.println("Choose an option:");
            decision = input.nextInt();
            switch (decision) { // The start of the loops//
                case 1:
                    playerNumber = random.nextInt(13) + 1;

                    playerNumber = playValue(playerNumber);
                    playerHandValue += playerNumber;
                    System.out.println("Your hand is: " + playerHandValue);
                    if (playerHandValue == 21) {
                        System.out.println("BLACKJACK! You win!");
                        newGame = true;
                        totalMatches++;
                        playerWins++;
                        gameNumber++;
                    } else if
                    (playerHandValue > 21) {
                        System.out.println("You exceeded 21! You lose.");
                        newGame = true;
                        totalMatches++;
                        dealerWins++;
                        gameNumber++;
                    }
                    break;
                case 2:
                    dealer = random.nextInt(11) + 16 ;
                    System.out.println("Dealer's hand: " + dealer);
                    System.out.println("Your hand is: " + playerHandValue);
                    if (dealer > 21 || dealer < playerHandValue  ) {
                        System.out.println("You win!");
                        newGame = true;
                        totalMatches++;
                        playerWins++;
                        gameNumber++;
                    } else if (dealer > playerHandValue) {
                        System.out.println("Dealer wins!");
                        newGame = true;
                        totalMatches++;
                        dealerWins++;
                        gameNumber++;
                    } else if (dealer == playerHandValue){
                        System.out.println("It's a tie! No one wins!");
                        newGame = true;
                        totalMatches++;
                        gameTie++;
                        gameNumber++;
                    }
                    break;
                case 3:
                    System.out.println("Number of Player wins: " + playerWins);
                    System.out.println("Number of Dealer wins: " + dealerWins);
                    System.out.println("Number of tie games: " + gameTie);
                    System.out.println("Total # of games played is: " + totalMatches);
                    double playerWins1 = playerWins;
                    double totalMatches1 = totalMatches;
                    double percent = playerWins1/totalMatches1 * 100.0;
                    System.out.println("Percentage of Player wins: " + String.format("%.1f",percent) + "%");
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
                    System.out.println("Please enter an integer value between 1 and 4.");
            }
        }// Added Comment for Testing
    }
}
