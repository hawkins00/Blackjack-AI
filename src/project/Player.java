package src.project;

import java.util.ArrayList;

/**
 * Created by Zosit on 11/22/2017.
 * This class acts as a player in a game of BlackJack (I think the dealer will also be here?).
 * They should be able to use a Shoe class to draw and discard cards from their hand.
 */
public class Player {
    private Shoe shoe;
    public ArrayList<Card> hand;

    //For dealer
    public Player() {
        shoe = new Shoe();
        shoe.createShoe();
        hand = new ArrayList<>();
    }

    //For player
    public Player(Shoe toShoe) {
        shoe = toShoe;
        hand = new ArrayList<>();
    }

    public void resetShoe() {
        shoe.createShoe();
    }

    public ArrayList<Card> getHand() {return hand;}

    public int getHandValue() {
        int total = 0;
        for (Card card: this.hand) {
            total += card.getValue();
        }

        return total;
    }

    public boolean isAceInHand() {
        for (Card card: this.hand) {
            if (card.getValue() == 1) {
                return true;
            }
        }

        return false;
    }

    public void setHand() {
        hand.add(shoe.drawCard());
        hand.add(shoe.drawCard());
    }

    public void discardHand() {hand = new ArrayList<>();}

    public void hit() {
        hand.add(shoe.drawCard());
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
