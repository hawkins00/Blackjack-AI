package src.project;

import java.util.Arrays;

/**
 * This class handles the Qlearning object and launches the GUI.
 */
public class Main {

    public static void main(String[] args) {
        Qlearning algorithm = new Qlearning();

        int episodes = 1000000;
        double eta = 0.01;
        double gamma = 0.01;
        double epsilonStart = 1.0;
        double epsilonMin   = 0.1;
        double epsilonDelta = 0.01;
        int epsilonEvery = (int)(episodes * epsilonDelta);

        algorithm.train(episodes, eta, gamma, epsilonStart, epsilonMin, epsilonDelta, epsilonEvery);

        double[] winningQ = algorithm.test(100000);
        double[] winningR = algorithm.randomTest(100000);

        System.out.println("----- Wins -----");
        System.out.println("Qlearn    Random");
        System.out.printf("%5.2f%%    %5.2f%% (win-loss)%n", winningQ[0]*100, winningR[0]*100);
        System.out.printf("%5.2f%%    %5.2f%% (win-push-loss)%n%n", winningQ[1]*100, winningR[1]*100);

        algorithm.prettyPrintQ(false);
        System.out.println("------------ Q-values ------------\n");
        algorithm.prettyPrintQ(true);

        BlackJackGUI blackJackGUI = new BlackJackGUI(algorithm);
        blackJackGUI.display();
    }
}
