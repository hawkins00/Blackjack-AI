package src.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

// TODO: a li'l bit o' everything

/**
 * Created by Zosit on 11/22/2017.
 * This is the Q-learning Class.
 * It should create a table of gamestates (with initial values).
 * It should create and run through a series of games to fill in the table
 */
public class Qlearning {

    private HashMap<String, Double[]> q;

    public Qlearning() {
        this.q = new HashMap<>();
    }

    /**
     * Train for #episodes.
     */
    public void train(int episodes, double eta, double gamma, double epsilon, double epsilonMin, double epsilonDelta, double epsilonEvery) {
        String state;
        String stateNew;
        int action;
        int reward;
        double stateAction;

        for (int i = 0; i < episodes;) {  // train for #episodes
            System.out.println(i);
            Game game = new Game();
            state = getState(game.getPlayerHand(), game.getDealerUpCard());

            while (game.isGameOver() == 0) {
                action = getAction(state, epsilon);
                reward = takeAction(action);
                stateNew = getState(game.getDealerHand(), game.getDealerUpCard());
                stateAction = getStateActionValue(state, action);
                double qNew = stateAction + eta * (reward + gamma * Collections.max(Arrays.asList(q.get(stateNew))) - stateAction);
              // update qNew in qState array, then put
              //  q.put(state, qNew); // //
                state = stateNew;
                i++; // only update when hand isn't instantly lost
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
     * @return The win percentage.
     */
    public double test(int episodes) {
        return 0.0;
    }

    // Private /////////////////////////////////////////////////////////////////

    /**
     * Get the current state in String format.
     * ([Player's hand values (ordered)]|[Dealer's face up card])
     * @return The current state.
     */
    private String getState(ArrayList<Card> hand, Card dealer) {
        StringBuilder state = new StringBuilder();
        Collections.sort(hand);
        for (Card card : hand) {
            state.append(card.getValue());
        }
        state.append("|");
        state.append(dealer.getValue());

        return state.toString();
    }

    /**
     * Get an action to take.
     * @return The an action to take.
     */
    private int getAction(String state, double epsilon) {
        if (Math.random() < epsilon) { // return random action
            return (int)(Math.random() * 2);
        } else { // return index of max action available
            return getActionMax(state);
        }
    }

    /**
     * Get the best action to take.
     * @return The best action to take.
     */
    private int getActionMax(String state) {
        Double[] qState = q.get(state);
        return qState[0] > qState[1] ? 0 : 1;
    }

    /**
     * Take the supplied action.
     */
    private int takeAction(int action) {
        return 0;
    }

    /**
     * Get the current State/Action value.
     * @param state The current state.
     * @param action The current action.
     * @return The value of provided state/action.
     */
    private double getStateActionValue(String state, int action) {
        return 0.0;
    }
}
