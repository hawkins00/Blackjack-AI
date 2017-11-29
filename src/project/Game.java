package src.project;

import java.util.ArrayList;

/**
 * Created by Zosit on 11/22/2017.
 * This will act as the rule manager of a game of blackjack.
 * It should create the shoe, player, and dealer.
 * It should contain functions for an outside source to manipulate the player,
 * with each input heading to the next game state needing player input.
 * It should also have functions that return data about the game state,
 */
public class Game {

    public enum GameState {
        IN_PROGRESS(0), LOSE(-1), PUSH(0), WIN(1);
        private final int val;
        GameState(int val) { this.val = val; }
        public int value() { return this.val; }
    }

    private Shoe shoe;
    private Player player;
    private Dealer dealer;
    private GameState gameState;

    //game initialization
    public Game() {
        shoe = new Shoe();
        shoe.createShoe();

        player = new Player();
        player.setHand(shoe.drawCard(), shoe.drawCard());

        dealer = new Dealer();
        dealer.setHand(shoe.drawCard(), shoe.drawCard());
        //test for win state on either side, end game immediately if there is one
        if(dealer.checkForBlackJack()) {
            if(player.checkForBlackJack()) {
                gameState = GameState.PUSH;
            }
            gameState = GameState.LOSE;
        } else {
            gameState = GameState.IN_PROGRESS;
        }
    }

    //game state reading functions
    public ArrayList<Card> getPlayerHand() {
        return player.getHand();
    }

    public int getPlayerHandValue() { return player.getHandValue(); }

    public boolean isAceInPlayerHand() { return player.isAceInHand(); }

    public ArrayList<Card> getDealerHand() {
        return dealer.getHand();
    }

    public Card getDealerUpCard() { return dealer.getInitialHand(); }

    public int getDealerUpCardValue() { return dealer.getInitialHand().getValue(); }

    public boolean isOver() {
        return this.gameState != GameState.IN_PROGRESS;
    }

    public int getScore() {
        return this.gameState.value();
    }

    //Game actions
    public void hit() {
        // TODO: player.hit();
        //TODO: test for fail state, giving the dealer the win
        if(dealer.checkForTurnCondition()) {
            // TODO: dealer.hit();
            //TODO: test for fail state, giving the player the win
        }
    }

    public void stand() {
        if(dealer.checkForTurnCondition()) {
            // TODO: dealer.hit();
            //TODO: test for fail state, giving the player the win
        } else {
            //TODO: compare hands to determine winner
        }
    }
}
