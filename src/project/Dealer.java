package src.project;

import java.util.ArrayList;

/**
 * Created by scottjones on 11/26/17.
 */
public class Dealer extends Object{

    private ArrayList<Card> hand;
    private Deck theDeck;

    //Deck to be initialized by the Game object
    public Dealer (Deck deck){
        hand = new ArrayList<>(2);
        theDeck = deck;
    }

    //player and dealer deal only to themselves
    public void deal(){
        hand.add(theDeck.drawCard());
        hand.add(theDeck.drawCard());
    }

    public boolean checkForBlackJack(){
        int aceFlag = 0;
        int faceFlag = 0;
        Card.Ranks rank;
        for(Card card: hand){
            rank = card.getRank();
            if(rank == Ranks.ACE){
                ++aceFlag;
            }
            else if(rank == Ranks.TEN || rank == Ranks.JACK || rank == Ranks.QUEEN || rank == Ranks.KING){
                ++faceFlag;
            }
        }
        if(aceFlag == 1 && faceFlag == 1) {
            return true;
        }
        return false;
    }

    public Card getInitialHand(){
        return hand.get(0);
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    //Dealer will hit on anything 16 or less
    public void turn(){
        if(checkForTurnCondition()){
            hit();
        }
    }

    private void hit(){
        hand.add(theDeck.drawCard());
    }

    private boolean checkForTurnCondition() {
        int pointTotal = 0;
        for (Card card: hand){
            pointTotal += card.getValue();
        }
        if(pointTotal < 17) {
            return true;
        }
        return false;
    }

}
