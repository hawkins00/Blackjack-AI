package src.project;

import java.util.ArrayList;

/**
 * Created by scottjones on 11/26/17.
 */
public class Dealer extends Player{

    public Dealer(Deck deck) {
        super(deck);
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

    public boolean checkForTurnCondition() {
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
