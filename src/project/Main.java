package src.project;

/**
 * Created by Zosit on 11/22/2017.
 * This class should just run the Qlearning algorithm
 * Maybe allow the input arguments to change variables like the learning rate and discount?
 */
public class Main {

    public static void main(String[] args) {
        Qlearning algorithm = new Qlearning();

        algorithm.train(100000, 0.2, 0.0005, 1, 0.1, 0.01, 1000);

        double[] winningQ = algorithm.test(10000, true);
        double[] winningR = algorithm.randomTest(10000, true);

        System.out.println("----- Wins -----");
        System.out.println("Qlearn    Random");
        System.out.printf("%5.2f%%    %5.2f%% %n", winningQ[0]*100, winningR[0]*100);
        System.out.printf("%5.2f%%    %5.2f%% (with pushes)%n%n", winningQ[1]*100, winningR[1]*100);

        algorithm.prettyPrintQ(false);
        algorithm.prettyPrintQ(true);

        BlackJackGUI blackJackGUI = new BlackJackGUI(algorithm);

        blackJackGUI.display();

    }
}
