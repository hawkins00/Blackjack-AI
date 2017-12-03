package src.project;

/**
 * Created by Zosit on 11/22/2017.
 * This class should just run the Qlearning algorithm
 * Maybe allow the input arguments to change variables like the learning rate and discount?
 */
public class Main {

    public static void main(String[] args) {
        Qlearning algorithm = new Qlearning();

        int episodes = 1000000;
        double eta = 0.2;
        double gamma = 0.0005;
        double epsilonStart = 1.0;
        double epsilonMin   = 0.1;
        double epsilonDelta = 0.01;
        int epsilonEvery = (int)(episodes * epsilonDelta);

        algorithm.train(episodes, eta, gamma, epsilonStart, epsilonMin, epsilonDelta, epsilonEvery);

        double[] winningQ = algorithm.test(1000);
        double[] winningR = algorithm.randomTest(1000);

        System.out.println("----- Wins -----");
        System.out.println("Qlearn    Random");
        System.out.printf("%5.2f%%    %5.2f%% %n", winningQ[0]*100, winningR[0]*100);
        System.out.printf("%5.2f%%    %5.2f%% (with pushes)%n%n", winningQ[1]*100, winningR[1]*100);

        algorithm.prettyPrintQ(false);
        System.out.println("------------ Q-values ------------\n");
        algorithm.prettyPrintQ(true);

        BlackJackGUI blackJackGUI = new BlackJackGUI(algorithm);

        blackJackGUI.display();

    }
}
