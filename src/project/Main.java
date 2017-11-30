package src.project;

/**
 * Created by Zosit on 11/22/2017.
 * This class should just run the Qlearning algorithm
 * Maybe allow the input arguments to change variables like the learning rate and discount?
 */
public class Main {

    public static void main(String[] args) {
        Qlearning algorithm = new Qlearning();

        //algorithm.randomTest(10);

        algorithm.train(100, 0.5, 0.5, 0.5, 0.0, 0.1, 50);

        // output Q matrix?

        algorithm.test(10);
    }
}
