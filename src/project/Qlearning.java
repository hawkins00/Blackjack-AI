package src.project;

// TODO: a li'l bit o' everything

/**
 * Created by Zosit on 11/22/2017.
 * This is the Q-learning Class.
 * It should create a table of gamestates (with initial values).
 * It should create and run through a series of games to fill in the table
 */
public class Qlearning {

    private double[][] q;
    private static int STATES  = 858 + 1; // 858 = largest legal state # possible (Ace 21 10 = 1 10101 1010);
    private static int ACTIONS = 2; // [0] = stand, [1] = hit

    public Qlearning() {
        this.q = new double[Qlearning.STATES][Qlearning.ACTIONS];
    }

    /**
     * Train for #episodes.
     */
    public void train(int episodes, double eta, double gamma, double epsilon, double epsilonMin, double epsilonDelta, int epsilonEvery) {
        int state;
        int stateNew;
        int action;
        int reward;
        double stateAction;

        for (int i = 0; i < episodes; i++) { // train for #episodes
            System.out.println(i);
            Game game = new Game();
            state = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());

            do {
                action = getAction(state, epsilon);
                reward = takeAction(action, game);
                stateNew = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());
                stateAction = q[state][action];
                q[state][action] = stateAction + eta * (reward + gamma * getActionMaxValue(stateNew) - stateAction);
                state = stateNew;
            } while (!game.isOver());

            // Update epsilon value periodically
            if ((i + 1) % epsilonEvery == 0) {
                if (epsilon > epsilonMin) {
                    epsilon -= epsilonDelta;
                } else {
                    epsilon = epsilonMin;
                }
            }
        }
    }

    /**
     * Test for #episodes. Return win %.
     *
     * @return The win percentage.
     */
    public double test(int episodes) {
        int state;
        int action;
        double rewardTotal = 0.0;

        for (int i = 0; i < episodes; i++) {
            Game game = new Game();
            state = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());

            do {
                action = getActionMax(state);
                rewardTotal += takeAction(action, game);
                state = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());
            } while (!game.isOver());
        }

        return rewardTotal / episodes;
    }

    // Private /////////////////////////////////////////////////////////////////

    /**
     * Get the current state.
     * State value = 1 bit for player has Ace/no Ace
     *             + 5 bits for player hand value
     *             + 4 bits for dealer exposed card value
     * @return The current state.
     */
    private int getState(int playerValue, boolean playerHasAce, int dealerValue) {
        int state = 16 * playerValue + dealerValue;
        if (playerHasAce) {
            state += 512;
        }

        return state;
    }

    /**
     * Get an action to take.
     *
     * @return The an action to take.
     */
    private int getAction(int state, double epsilon) {
        if (Math.random() < epsilon) { // return random action
            return (int) (Math.random() * 2);
        } else { // return index of max action available
            return getActionMax(state);
        }
    }

    /**
     * Get the best action to take.
     *
     * @return The best action to take.
     */
    private int getActionMax(int state) {
        return this.q[state][0] > this.q[state][1] ? 0 : 1;
    }

    /**
     * Get the best available action's value.
     *
     * @return The best action value.
     */
    private double getActionMaxValue(int state) {
        return this.q[state][0] > this.q[state][1] ? this.q[state][0] : this.q[state][1];
    }

    /**
     * Take the supplied action.
     * @return The value of the action taken.
     */
    private int takeAction(int action, Game game) {
        if (action == 0) {
            game.stand();
        } else {
            game.hit();
        }

        return game.getScore();
    }
}
