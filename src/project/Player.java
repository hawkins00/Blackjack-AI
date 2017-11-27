package src.project;

import java.util.ArrayList;

/**
 * Created by Zosit on 11/22/2017.
 * This class acts as a player in a game of BlackJack (I think the dealer will also be here?).
 * They should be able to use a Deck class to draw and discard cards from their hand.
 */
public class Player {
    private Deck deck;
    public ArrayList<Card> hand;

    //For dealer
    public Player() {
        deck = new Deck();
        deck.createShoe();
        hand = new ArrayList<>();
    }

    //For player
    public Player(Deck toDeck) {
        deck = toDeck;
        hand = new ArrayList<>();
    }

    public void resetShoe() {
        deck.createShoe();
    }

    public ArrayList<Card> getHand() {return hand;}

    public void setHand() {
        hand.add(deck.drawCard());
        hand.add(deck.drawCard());
    }

    public void discardHand() {hand = new ArrayList<>();}

    public void hit() {
        hand.add(deck.drawCard());
    }

    public boolean checkForBlackJack(){
        boolean aceFlag = false;
        boolean faceFlag = false;
        Card.Ranks rank;
        if (hand.size() > 2) {
            return false;
        }
        for(Card card: hand){
            rank = card.getRank();
            if(rank == Card.Ranks.ACE){
                aceFlag = true;
            }
            else if(card.isFace()){
                faceFlag = true;
            }
        }
        if(aceFlag && faceFlag) {
            return true;
        }

        return false;
    }
}
