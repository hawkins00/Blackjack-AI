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

    public Qlearning() {
        this.q = new double[1024][2];
    }

    /**
     * Train for #episodes.
     */
    public void train(int episodes, double eta, double gamma, double epsilon, double epsilonMin, double epsilonDelta, double epsilonEvery) {
        int state;
        int stateNew;
        int action;
        int reward;
        double stateAction;

        for (int i = 0; i < episodes; ) {  // train for #episodes
            System.out.println(i);
            Game game = new Game();
            state = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());

            while (game.isGameOver() == 0) { // account for pushes??? \\
                action = getAction(state, epsilon);
                reward = takeAction(action);
                stateNew = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());
                stateAction = q[state][action];
                q[state][action] = stateAction + eta * (reward + gamma * getActionMaxValue(stateNew) - stateAction);
                state = stateNew;
                i++; // only count episode when hand isn't instantly lost (something to learn)
            }

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
        return 0.0;
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
     */
    private int takeAction(int action) {
        return 0;
    }
}
