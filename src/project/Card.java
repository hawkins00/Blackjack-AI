package src.project;

/**
 * Created by Zosit on 11/22/2017.
 * This class acts as a card in a typical BlackJack deck
 */
public class Card {
  public enum Suits {
    DIAMONDS, HEARTS, SPADES, CLUBS
  }
  public enum Ranks {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
  }

  private Suits suit;
  private Ranks rank;
  private int value;

  //constructor
  public Card(Suits newSuit, Ranks newRank) {
    this.suit = newSuit;
    this.rank = newRank;

    // This whole mess assigns a value to its corresponding rank.
    // I assign a value of 1 to aces; we should check in the game class whether they should be 1 or 11.
    if (this.rank == Ranks.ACE) {
      this.value = 1;
    }
    else if (this.rank == Ranks.TWO) {
      this.value = 2;
    }
    else if (this.rank == Ranks.THREE) {
      this.value = 3;
    }
    else if (this.rank == Ranks.FOUR) {
      this.value = 4;
    }
    else if (this.rank == Ranks.FIVE) {
      this.value = 5;
    }
    else if (this.rank == Ranks.SIX) {
      this.value = 6;
    }
    else if (this.rank == Ranks.SEVEN) {
      this.value = 7;
    }
    else if (this.rank == Ranks.EIGHT) {
      this.value = 8;
    }
    else if (this.rank == Ranks.NINE) {
      this.value = 9;
    }
    else {
      this.value = 10;
    }
  }

  public Suits getSuit() {
    return this.suit;
  }

  public Ranks getRank() {
    return this.rank;
  }

  public int getValue() {
    return this.value;
  }
}
