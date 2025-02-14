/*
Assignment:
Making a Card Game with OOD Group Activity

Kyle Cook
*/

package cardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
    private static ArrayList<Card> playerCards = new ArrayList<Card>();
    private static Scanner scanner = new Scanner(System.in);
    private static int totalPairsFound = 0;
    private static int totalPairsNotFound = 0;
    private static int totalRoundsPlayed = 0;
    private static boolean gameActive = true;

    public static void main(String[] args) {
        loadDeck();
        System.out.println("=== Card Matching Game ===");
        
        do {
            totalRoundsPlayed++;
            deckOfCards.clear();
            loadDeck();
            playerCards.clear();
            shuffle();
            dealCards();

            System.out.println("\nPlayer's Cards:");
            displayPlayerCards();

            boolean hasPair = checkForPairs();
            if (hasPair) {
                totalPairsFound++;
                System.out.println("Pair found!");
            } else {
                totalPairsNotFound++;
                System.out.println("No pairs found.");
            }

            System.out.println("\n=== Current Statistics ===");
            System.out.println("Rounds Played: " + totalRoundsPlayed);
            System.out.println("Pairs Found: " + totalPairsFound);
            System.out.println("Pairs Not Found: " + totalPairsNotFound);

            boolean validInput = false;
            while (!validInput) {
                System.out.print("\nPlay another round? (Y/N): ");
                System.out.flush();
                try {
                    String response = scanner.next();
                    if (response.equalsIgnoreCase("Y")) {
                        gameActive = true;
                        validInput = true;
                    } else if (response.equalsIgnoreCase("N")) {
                        gameActive = false;
                        validInput = true;
                    }
                    scanner.nextLine(); // Clear the buffer
                } catch (Exception e) {
                    scanner.nextLine(); // Clear the buffer on error
                }
            }
        } while (gameActive);

        System.out.println("\n=== Final Game Statistics ===");
        System.out.println("Total Rounds Played: " + totalRoundsPlayed);
        System.out.println("Total Pairs Found: " + totalPairsFound);
        System.out.println("Total Pairs Not Found: " + totalPairsNotFound);
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static void loadDeck() {
        Scanner input = null;
        try {
            input = new Scanner(new File("cards.txt"));
            while (input.hasNext()) {
                String[] fields = input.nextLine().split(",");
                Card newCard = new Card(fields[0], fields[1].trim(), Integer.parseInt(fields[2].trim()), fields[3]);
                deckOfCards.add(newCard);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading deck.");
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    private static void shuffle() {
        for (int i = 0; i < deckOfCards.size(); i++) {
            int index = (int) (Math.random() * deckOfCards.size());
            Card c = deckOfCards.remove(index);
            deckOfCards.add(c);
        }
    }

    private static void dealCards() {
        for (int i = 0; i < 5; i++) {
            playerCards.add(deckOfCards.remove(0));
        }
    }

    private static void displayPlayerCards() {
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.println((i + 1) + ". " + playerCards.get(i));
        }
    }

    private static boolean checkForPairs() {
        for (int i = 0; i < playerCards.size(); i++) {
            for (int j = i + 1; j < playerCards.size(); j++) {
                if (playerCards.get(i).equals(playerCards.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }
}
