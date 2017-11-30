package src.project;

/**
 * Created by scottjones on 11/26/17.
 */
public class Dealer extends Player{

    public Dealer() {
    }

    public Card getFirstCard(){
        return hand.get(0);
    }

    public boolean shouldHit() {
        int pointTotal = 0;
        for (Card card: hand){
            pointTotal += card.getValue();
        }
        return pointTotal < 17;
    }

}
