package src.project;

/**
 * Created by Zosit on 11/22/2017.
 * This class acts as a card in a typical BlackJack deck
 */
public class Card {
    private char suit;
    private int  value;

    //constructor
    public Card(char newSuit, int newValue) {
        this.suit = newSuit;
        this.value = newValue;
    }

    public char getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }
}
