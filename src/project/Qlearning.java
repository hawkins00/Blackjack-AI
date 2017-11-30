package src.project;

/**
 * The Qlearning class
 * 1. Create a Qlearning object.
 * 2. Call Qlearning.train(...).
 * 3. Call Qlearning.test(...).
 * 4. ???
 * 5. Profit.
 */
public class Qlearning {

    private double[][] q;
    private static int ACTIONS = 2; // [0] = stand, [1] = hit
    private static int STATES  = 858 + 1; // 858 = largest legal state # possible
                                          //       Ace 21 10 [in decimal] = 1 10101 1010 [in binary]);

    /**
     * Qlearning constuctor
     */
    public Qlearning() {
        this.q = new double[Qlearning.STATES][Qlearning.ACTIONS];
    }

    /**
     * Train with Q Learning.
     * @param episodes Number of episodes (games) to play/train.
     * @param eta Learning rate
     * @param gamma Gamma value
     * @param epsilon Initial epsilon value
     * @param epsilonMin Minimum epsilon value
     * @param epsilonDelta Amount to change epsilon every epsilonEvery episodes
     * @param epsilonEvery Number of episodes to train before reducing epsilon by epsilonDelta
     */
    public void train(int episodes, double eta, double gamma, double epsilon, double epsilonMin, double epsilonDelta, int epsilonEvery) throws IllegalArgumentException {
        if (eta <= 0.0 || gamma < 0.0 || epsilon < 0.0 || epsilonMin < 0.0 || epsilonDelta < 0.0 ||
            eta > 1.0  || gamma > 1.0 || epsilon > 1.0 || epsilonMin > 1.0 || epsilonDelta > 1.0 ||
            epsilonEvery >= episodes || epsilonMin > epsilon || episodes <= 0 || epsilonEvery < 1) {
            throw new IllegalArgumentException("Illegal argument(s) passed to Qlearning.train()");
        }
        int state;
        int stateNew;
        int action;
        int reward;

        for (int i = 0; i < episodes; i++) { // train for #episodes
            System.out.println(i);
            Game game = new Game();
            state = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());

            do {
                action = getAction(state, epsilon);
                reward = takeAction(action, game);
                stateNew = getState(game.getPlayerHandValue(), game.isAceInPlayerHand(), game.getDealerUpCardValue());
                q[state][action] += eta * (reward + gamma * getActionMaxValue(stateNew) - q[state][action]);
                state = stateNew;
            } while (!game.isOver());

            // Update epsilon value periodically
            if ((i + 1) % epsilonEvery == 0) {
                if (epsilon > epsilonMin) {
                    epsilon -= epsilonDelta;
                }
                if (epsilon < epsilonMin) {
                    epsilon = epsilonMin;
                }
            }
        }
    }

    /**
     * Test for #episodes. Return win %.
     * @param episodes Number of episodes (games) to play
     * @return The win percentage.
     */
    public double test(int episodes) {
        if (episodes < 1) {
            throw new IllegalArgumentException("Illegal argument passed to Qlearning.test()");
        }
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

    /**
     * Randomly play for #episodes. Return win %.
     * @param episodes Number of episodes (games) to play.
     * @return The win percentage.
     */
    public double randomTest(int episodes) {
        if (episodes < 1) {
            throw new IllegalArgumentException("Illegal argument passed to Qlearning.randomTest()");
        }

        double rewardTotal = 0.0;

        for (int i = 0; i < episodes; i++) {
            Game game = new Game();
            do {
                rewardTotal += takeAction(getActionRandom(), game);
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
     * @param playerValue The player's minimum total hand (Ace = 1).
     * @param playerHasAce If the player has at least one Ace in hand.
     * @param dealerValue The value of the dealer's exposed card.
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
     * Get an epsilon-greedy action to take.
     * @param state The current state.
     * @param epsilon The chance of selecting a random action.
     * @return The an action to take.
     */
    private int getAction(int state, double epsilon) {
        if (Math.random() < epsilon) { // return random action
            return getActionRandom();
        } else { // return index of max action available
            return getActionMax(state);
        }
    }

    /**
     * Get the best action to take.
     * @param state The current state.
     * @return The best action to take.
     */
    private int getActionMax(int state) {
        return this.q[state][0] > this.q[state][1] ? 0 : 1;
    }

    /**
     * Get a random action to take.
     * @return The random action to take.
     */
    private int getActionRandom() {
        return (int) (Math.random() * Qlearning.ACTIONS);
    }

    /**
     * Get the best value of the available actions at this state.
     * @param state The state to observe.
     * @return The best action value.
     */
    private double getActionMaxValue(int state) {
        return this.q[state][getActionMax(state)];
    }

    /**
     * Take the supplied action, get the reward.
     * @param action The action to take.
     * @param game The game in which to take the action.
     * @return The reward value of the action taken.
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
