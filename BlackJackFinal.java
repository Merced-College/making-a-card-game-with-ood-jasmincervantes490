//Group Member Names: 
//Jasmin Cervantes
//Arnold Rocha
//Juan Padilla
//Angel Cortez
//Used AI to help convert Array into an ArrayList
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {

  private static ArrayList<Card> deck = new ArrayList<>();
  private static int currentCardIndex = 0;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean turn = true;

    while (turn) {
      initializeDeck();
      shuffleDeck();
      int playerTotal = dealInitialPlayerCards();
      int dealerTotal = dealInitialDealerCards();

      playerTotal = playerTurn(scanner, playerTotal);
      if (playerTotal > 21) {
        System.out.println("You busted! Dealer wins.");
          continue; // Continue to the next hand
      }

      dealerTotal = dealerTurn(dealerTotal);
      determineWinner(playerTotal, dealerTotal);

      // Ask player if they want to play again
      System.out.println("Would you like to play another hand? (yes/no)");
      String playerDecision = scanner.nextLine().toLowerCase();

      while (!(playerDecision.equals("no") || playerDecision.equals("yes"))) {
        System.out.println("Invalid action. Please type 'yes' or 'no'.");
        playerDecision = scanner.nextLine().toLowerCase();
      }

      turn = playerDecision.equals("yes");
      }

      System.out.println("Thanks for playing!");
      scanner.close(); // Close the scanner
    }

    // Algorithm to create deck
    private static void initializeDeck() {
      List<String> suits = new ArrayList<>(); //Converts Array suits into an ArrayList
      List<String> ranks = new ArrayList<>(); //Converts Array ranks into an ArrayList

      // Add suits to the list
      suits.add("Hearts");
      suits.add("Diamonds");
      suits.add("Clubs");
      suits.add("Spades");

      // Add ranks to the list
      ranks.add("2");
      ranks.add("3");
      ranks.add("4");
      ranks.add("5");
      ranks.add("6");
      ranks.add("7");
      ranks.add("8");
      ranks.add("9");
      ranks.add("10");
      ranks.add("Jack");
      ranks.add("Queen");
      ranks.add("King");
      ranks.add("Ace");

      // Clear the deck before adding new cards
      deck.clear();

      // Create the deck
      for (String suit : suits) {
        for (String rank : ranks) {
          int value;
          if (rank.equals("Ace")) {
            value = 11; // Ace value (can be adjusted if needed)
          } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
              value = 10; // Face card value
          } else {
              value = Integer.parseInt(rank); // Numeric value
          }
          deck.add(new Card(value, suit, rank));
          }
        }
    }

    // Algorithm to shuffle deck
    private static void shuffleDeck() {
        Collections.shuffle(deck, new Random());
        currentCardIndex = 0; // Reset the card index
    }

    // Algorithm to deal initial player cards
    private static int dealInitialPlayerCards() {
        Card card1 = dealCard();
        Card card2 = dealCard();
        System.out.println("Your cards: " + card1.getRank() + " of " + card1.getSuit() + " and " + card2.getRank() + " of " + card2.getSuit());
        return card1.getValue() + card2.getValue();
    }

    // Algorithm to deal initial dealer cards
    private static int dealInitialDealerCards() {
        Card card1 = dealCard();
        System.out.println("Dealer's card: " + card1);
        return card1.getValue();
    }

    private static int playerTurn(Scanner scanner, int playerTotal) {
      while (true) {
        System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
        String action = scanner.nextLine().toLowerCase();
        if (action.equals("hit")) {
          Card newCard = dealCard();
          playerTotal += newCard.getValue();
          System.out.println("You drew a " + newCard);
          if (playerTotal > 21) {
            System.out.println("You busted! Dealer wins.");
            return 0; // Return 0 to indicate bust
          }
          } else if (action.equals("stand")) {
              break;
          } else {
              System.out.println("Invalid action. Please type 'hit' or 'stand'.");
          }
        }
        return playerTotal;
    }

    // Algorithm for dealer's turn
    private static int dealerTurn(int dealerTotal) {
      while (dealerTotal < 17) {
        Card newCard = dealCard();
        dealerTotal += newCard.getValue();
      }
      System.out.println("Dealer's total is " + dealerTotal);
      return dealerTotal;
    }

    // Algorithm to determine the winner
    private static void determineWinner(int playerTotal, int dealerTotal) {
      if (dealerTotal > 21 || playerTotal > dealerTotal) {
        System.out.println("You win!");
      } else if (dealerTotal == playerTotal) {
          System.out.println("It's a tie!");
      } else {
          System.out.println("Dealer wins!");
      }
    }

    // Algorithm to deal a card
    private static Card dealCard() {
      if (currentCardIndex >= deck.size()) {
        System.out.println("The deck is out of cards. Reshuffling...");
        initializeDeck(); // Reinitialize deck if out of cards
        shuffleDeck();
      }
      return deck.get(currentCardIndex++);
    }
}

