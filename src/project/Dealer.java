package src.project;

/**
 * Created by scottjones on 11/26/17.
 */
public class Dealer extends Player{

    public Dealer() {
    }

    public Card getInitialHand(){
        return hand.get(0);
    }

    //Dealer will hit on anything 16 or less
    public void turn(){
        if(checkForTurnCondition()){
            // TODO: hit();
        }
    }

    public boolean checkForTurnCondition() {
        int pointTotal = 0;
        for (Card card: hand){
            pointTotal += card.getValue();
        }
        return pointTotal < 17;
    }

}
