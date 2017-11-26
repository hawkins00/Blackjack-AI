package src.project;

import java.util.ArrayList;

/**
 * Created by scottjones on 11/26/17.
 */
public class Dealer extends Player{

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

    public Card getInitialHand(){
        return hand.get(0);
    }

    //Dealer will hit on anything 16 or less
    public void turn(){
        if(checkForTurnCondition()){
            hit();
        }
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
