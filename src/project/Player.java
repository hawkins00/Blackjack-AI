package src.project;

import java.util.ArrayList;

/**
 * Created by Zosit on 11/22/2017.
 * This class acts as a player in a game of BlackJack (I think the dealer will also be here?).
 * They should be able to use a Shoe class to draw and discard cards from their hand.
 */
public class Player {

    ArrayList<Card> hand;

    public Player() {
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getHandValue() {
        int total = 0;
        for (Card c : hand) {
            total += c.getValue();
        }
        return total;
    }

    public boolean hasAce() {
        for (Card c : hand) {
            if (c.isAce()) {
                return true;
            }
        }
        return false;
    }

    public void setHand(Card first, Card second) {
        hand.add(first);
        hand.add(second);
    }

    public void discardHand() {
        hand = new ArrayList<>();
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public boolean has21() {
        int total = 0;
        for (Card c : hand) {
            total += c.getValue();
        }
        return total == 21;
    }

    public boolean hasBlackjack() {
        return hand.size() == 2 &&
                ((hand.get(0).isFace() && hand.get(1).isAce()) ||
                        (hand.get(0).isAce() && hand.get(1).isFace()));
    }
}
