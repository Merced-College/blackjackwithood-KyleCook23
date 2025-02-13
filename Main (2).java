/*
Assignment:
Making a Card Game with OOD Group Activity

Kyle Cook
*/

package cardGame;
//importing the scanner class
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  
  private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
  private static ArrayList<Card> playerCards = new ArrayList<Card>();


  public static void main(String[] args) {
    //creates a deck of cards
    Scanner input = null;
    try {
      input = new Scanner(new File("cards.txt"));
    } catch (FileNotFoundException e) {
      
      // error
      System.out.println("error");
      e.printStackTrace();
    }
   
    while(input.hasNext()) {
      String[] fields  = input.nextLine().split(",");
      Card newCard = new Card(fields[0], fields[1].trim(),
          Integer.parseInt(fields[2].trim()), fields[3]);
      deckOfCards.add(newCard);	
    }

    shuffle();

    //deal the player 5 cards
    for(int i = 0; i < 4; i++) {
      playerCards.add(deckOfCards.remove(i));
    }

    System.out.println("players cards");
    for(Card c: playerCards)
      System.out.println(c);

    System.out.println("pairs is " + checkFor2Kind());

  }//end main

  public static void shuffle() {

    //shuffling the cards by deleting and reinserting
    for (int i = 0; i < deckOfCards.size(); i++) {
      int index = (int) (Math.random()*deckOfCards.size());
      Card c = deckOfCards.remove(index);
      deckOfCards.add(c);
    }
  }

  //check for 2 of a kind in the players hand
  public static boolean checkFor2Kind() {

    int count = 0;
    for(int i = 0; i < playerCards.size() - 1; i++) {
      Card current = playerCards.get(i);
      Card next = playerCards.get(i+1);

      for(int j = i+1; j < playerCards.size(); j++) {
        next = playerCards.get(j);
        if(current.equals(next))
          count++;
      }//end of inner for
      if(count == 1)
        return true;

    }//end outer for
    return false;
  }
}//end class