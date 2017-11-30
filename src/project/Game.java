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

        private final int value;

        GameState(int val) { this.value = val; }

        public int value() { return this.value; }
    }

    private Shoe shoe;
    private Player player;
    private Dealer dealer;
    private GameState gameState;

    public Game() {
        shoe = new Shoe();
        shoe.createShoe();

        player = new Player();
        dealer = new Dealer();

        dealCards();
    }

    public void dealCards() {
        // 31 is the minimum number of cards needed to sustain a round
        // (worst case hand sizes: player gets 21 aces, dealer gets 3 aces and 7 twos)
        if (shoe.getShoeSize() < 31) {
            shoe.createShoe();
        }

        // In reality the cards are dealt in an alternating fashion, but this is fine
        player.setHand(shoe.drawCard(), shoe.drawCard());
        dealer.setHand(shoe.drawCard(), shoe.drawCard());

        if (dealer.hasBlackjack()) {
            if (player.hasBlackjack()) {
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

    public int getPlayerHandMinValue() { return player.getHandMinValue(); }

    public boolean playerHasAce() { return player.hasAce(); }

    public ArrayList<Card> getDealerHand() {
        return dealer.getHand();
    }

    public Card getDealerUpCard() { return dealer.getFirstCard(); }

    public int getDealerUpCardValue() { return dealer.getFirstCard().getValue(); }

    public boolean isOver() {
        return this.gameState != GameState.IN_PROGRESS;
    }

    public int getScore() {
        return this.gameState.value();
    }

    // Game actions

    public void playerHit() {
        player.hit(shoe.drawCard());
        if (player.getHandValue() > 21) {
            gameState = GameState.LOSE;
        }
    }

    public void playerStand() {
        while (dealer.shouldHit()) {
            dealer.hit(shoe.drawCard());
        }
        if (dealer.getHandValue() > 21) {
            gameState = GameState.WIN;
        } else {
            if (player.getHandValue() > dealer.getHandValue()) {
                gameState = GameState.WIN;
            } else if (player.getHandValue() < dealer.getHandValue()) {
                gameState = GameState.LOSE;
            } else {
                gameState = GameState.PUSH;
            }
        }
    }
}
