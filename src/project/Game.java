package src.project;

import java.util.ArrayList;

/**
 * Created by Zosit on 11/22/2017.
 * This will act as the rule manager of a game of blackjack.
 * It should create the deck, player, and dealer.
 * It should contain functions for an outside source to manipulate the player,
 * with each input heading to the next game state needing player input.
 * It should also have functions that return data about the game state,
 */
public class Game {

    private Deck deck;
    private Player player;
    private Dealer dealer;
    private int winState;

    //game initialization
    public Game() {
        deck = new Deck();
        deck.createShoe();

        player = new Player(deck);
        player.setHand();

        dealer = new Dealer(deck);
        dealer.setHand();
        //test for win state on either side, end game immediately if there is one
        if(dealer.checkForBlackJack()) {
            if(player.checkForBlackJack()) {
                winState = 1;
            }
            winState = -1;
        } else {
            winState = 0;
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

    public int isGameOver() {
        return winState;
    }

    //Game actions
    public void hit() {
        player.hit();
        //TODO: test for fail state, giving the dealer the win
        if(dealer.checkForTurnCondition()) {
            dealer.hit();
            //TODO: test for fail state, giving the player the win
        }
    }

    public void stand() {
        if(dealer.checkForTurnCondition()) {
            dealer.hit();
            //TODO: test for fail state, giving the player the win
        } else {
            //TODO: compare hands to determine winner
        }
    }
}
