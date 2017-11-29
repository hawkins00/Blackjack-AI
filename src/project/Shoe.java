package src.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Zosit on 11/22/2017.
 * This class shall utilize the Card class to create and manipulate a BlackJack deck of cards
 */
public class Shoe {

  private ArrayList<Card> shoe;

  public void createShoe() {
    ArrayList<Card> newShoe = new ArrayList<>();

    // creates 6 decks and shuffles them together before returning the shoe
    for (int i = 0; i < 6; i++) {
      for (Card.Suit s : Card.Suit.values()) {
        for (Card.Rank r : Card.Rank.values()) {
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

  public int getShoeSize() {
    return this.shoe.size();
  }
}
