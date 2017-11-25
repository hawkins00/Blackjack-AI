package src.project;

/**
 * Created by Zosit on 11/22/2017.
 * This class should just run the Qlearning algorithm
 * Maybe allow the input arguments to change variables like the learning rate and discount?
 */
public class Main {

  public static void main(String[] args) {
    // This is just to make sure the shoe initialized correctly.  Feel free to comment out/delete.
    Deck d = new Deck();
    d.createShoe();
    System.out.println("Size of shoe: " + d.getShoeSize());

    while (d.getShoeSize() != 0) {
      Card c = d.drawCard();
      System.out.println(c.getRank() + " " + c.getSuit() + " " + c.getValue());
    }
  }
}
