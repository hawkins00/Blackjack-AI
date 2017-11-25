package src.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Zosit on 11/22/2017.
 * This class shall utilize the Card class to create and manipulate a BlackJack deck of cards
 */
public class Deck {
  public enum Suits {
    DIAMONDS, HEARTS, SPADES, CLUBS
  }
  public enum Ranks {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
  }
  private ArrayList<Card> shoe;

  public void createShoe() {
    ArrayList<Card> newShoe = new ArrayList<>();

    // creates 6 decks and shuffles them together before returning the shoe
    for (int i = 0; i < 6; i++) {
      for (Suits s : Suits.values()) {
        for (Ranks r : Ranks.values()) {
          newShoe.add(new Card(s, r));
        }
      }
    }

    Collections.shuffle(newShoe);
    this.shoe = newShoe;
  }

  // remove a card from index 0 of the shoe (since it's randomized) and return it
  public Card drawCard() {
    return this.shoe.remove(0);
  }

  public ArrayList<Card> getShoe() {
    return this.shoe;
  }
}
