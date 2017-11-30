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
        int plotEvery = episodes > 50 ? episodes / 50 : episodes;
        int state;
        int stateNew;
        int action;
        int reward;

        System.out.printf("%nLearning Q-style    .    .    .    .    .%9d %n", episodes);

        for (int i = 0; i < episodes; i++) { // train for #episodes
            Game game = new Game();
            state = getState(game);

            do {
                action = getAction(state, epsilon);
                reward = takeAction(action, game);
                stateNew = getState(game);
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

            if ((i+1) % plotEvery == 0) {
                System.out.print('>');
            }
        }
        System.out.println("\n");
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
        int wins = 0;
        int pushes = 0;

        for (int i = 0; i < episodes; i++) {
            Game game = new Game();
            state = getState(game);

            do {
                action = getActionMax(state);
                takeAction(action, game);
                state = getState(game);
            } while (!game.isOver());
            if (game.getScore() > 0) {
                wins++;
            }
        }

        return wins / (double)episodes;
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

        int wins = 0;

        for (int i = 0; i < episodes; i++) {
            Game game = new Game();

            do {
                takeAction(getActionRandom(), game);
            } while (!game.isOver());

            if (game.getScore() > 0) {
                wins++;
            }
        }

        return wins / (double)episodes;
    }

    /**
     * Get the Q table.
     * @return The Q table.
     */
    public double[][] getQ() {
        return q;
    }

    /**
     * Pretty Print the Q table.
     */
    public void prettyPrintQ(boolean printValues) {
        double stand;
        double hit;

        System.out.println(" Player    Dealer    Stand    Hit");
        System.out.println("----------------------------------");

        for (int i = 0; i < Qlearning.STATES; i++) {
            stand = this.q[i][0];
            hit = this.q[i][1];
            if (stand != 0.0 && hit != 0.0) {
                if (printValues) {
                    System.out.printf("%9s %11.2f %7.2f %n", unGetState(i), stand, hit);
                } else {
                    if (stand > hit) {
                        System.out.printf("%9s %9s %7s %n", unGetState(i), "X", "-");
                    } else {
                        System.out.printf("%9s %9s %7s %n", unGetState(i), "-", "X");
                    }
                }
            }
        }

    }

    // Private /////////////////////////////////////////////////////////////////

    /**
     * Get the current state.
     * State value = 1 bit for player has Ace/no Ace
     *             + 5 bits for player hand value
     *             + 4 bits for dealer exposed card value
     * @param game The current game.
     * @return The current state.
     */
    private int getState(Game game) {
        int state = 16 * game.getPlayerHandMinValue() + game.getDealerUpCardValue();
        if (game.playerHasAce()) {
            state += 512;
        }

        return state;
    }

    /**
     * Get card information from a provided state.
     * @param state The state to unget.
     * @return the card info. available from the state.
     */
    private String unGetState(int state) {
        int dealer = state % 16;
        state /= 16;
        int player = state % 32;
        boolean hasAce = state / 32 == 1;

        return String.format("%2d %s   %2d", player, hasAce? "w/ Ace" : "no Ace", dealer);
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
        return this.q[state][0] >= this.q[state][1] ? 0 : 1;
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
        return state > Qlearning.STATES ? -1 : this.q[state][getActionMax(state)];
    }

    /**
     * Take the supplied action, get the reward.
     * @param action The action to take.
     * @param game The game in which to take the action.
     * @return The reward value of the action taken.
     */
    private int takeAction(int action, Game game) {
        if (action == 0) {
            game.playerStand();
        } else {
            game.playerHit();
        }

        return game.getScore();
    }
}
