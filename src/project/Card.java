package src.project;

/**
 * Created by Zosit on 11/22/2017.
 * This class acts as a card in a typical BlackJack deck
 */
public class Card {
  private Deck.Suits suit;
  private Deck.Ranks rank;
  private int value;

  //constructor
  public Card(Deck.Suits newSuit, Deck.Ranks newRank) {
    this.suit = newSuit;
    this.rank = newRank;

    // This whole mess assigns a value to its corresponding rank.
    // I assign a value of 1 to aces; we should check in the game class whether they should be 1 or 11.
    if (this.rank == Deck.Ranks.ACE) {
      this.value = 1;
    }
    else if (this.rank == Deck.Ranks.TWO) {
      this.value = 2;
    }
    else if (this.rank == Deck.Ranks.THREE) {
      this.value = 3;
    }
    else if (this.rank == Deck.Ranks.FOUR) {
      this.value = 4;
    }
    else if (this.rank == Deck.Ranks.FIVE) {
      this.value = 5;
    }
    else if (this.rank == Deck.Ranks.SIX) {
      this.value = 6;
    }
    else if (this.rank == Deck.Ranks.SEVEN) {
      this.value = 7;
    }
    else if (this.rank == Deck.Ranks.EIGHT) {
      this.value = 8;
    }
    else if (this.rank == Deck.Ranks.NINE) {
      this.value = 9;
    }
    else {
      this.value = 10;
    }
  }

  public Deck.Suits getSuit() {
    return this.suit;
  }

  public Deck.Ranks getRank() {
    return this.rank;
  }

  public int getValue() {
    return this.value;
  }
}
