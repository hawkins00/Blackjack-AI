package src.project;
//************************************************************
//
//  BlackJackGUI.java       Authors: Lewis, Chase, Coleman
//
//  Provides a graphical user interface for a blackjack game
//  using the BlackJack class to provide the functionality
//  of the game
//
//************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BlackJackGUI extends JPanel
{
    JPanel topPanel = new JPanel();
    JPanel dcardPanel = new JPanel();
    JPanel pcardPanel = new JPanel();
    JTextPane winlosebox = new JTextPane();
    JButton nextbutton = new JButton();
    JButton dealbutton = new JButton();
//    JButton staybutton = new JButton();
//    JButton playagainbutton = new JButton();
    JLabel dealerlabel = new JLabel();
    JLabel playerlabel = new JLabel();

    Qlearning qlearning;
    Game game;

    private Player player;
    private Dealer dealer;
//    private ArrayList<CardGUI> dscan = new ArrayList<>();
//    private ArrayList<CardGUI> pscan = new ArrayList<>();
    DeckGUI deckGUI = new DeckGUI();

    /*************************************************************
     the labels to represent the cards for the game
     *************************************************************/
    JLabel playercard1;
    JLabel playercard2;
    JLabel playercardhit;
    JLabel dealercard0;
    JLabel dealercard2;
    JLabel dealercard1;
    JLabel dealercardhit;

    /*************************************************************
     Constructs the screen
     *************************************************************/
    public BlackJackGUI (Qlearning qlearning)
    {
        this.qlearning = qlearning;

        topPanel.setBackground(new Color(0, 122, 0));
        dcardPanel.setBackground(new Color(0, 122, 0));
        pcardPanel.setBackground(new Color(0, 122, 0));

        topPanel.setLayout(new FlowLayout());
        winlosebox.setText(" ");
        winlosebox.setFont(new java.awt.Font("Helvetica Bold", 1, 20));
        dealbutton.setText("  Deal");
        dealbutton.addActionListener(new dealbutton());
        nextbutton.setText("  Next");
        nextbutton.addActionListener(new nextbutton());
        nextbutton.setEnabled(false);
//        staybutton.setText("  Stay");
////        staybutton.addActionListener(new staybutton());
//        staybutton.setEnabled(false);
//        playagainbutton.setText("  Play Again");
////        playagainbutton.addActionListener(new playagainbutton());
//        playagainbutton.setEnabled(false);

        dealerlabel.setText("  Dealer:  ");
        playerlabel.setText("  Player:  ");

        topPanel.add(winlosebox);
        topPanel.add(dealbutton);
        topPanel.add(nextbutton);
//        topPanel.add(staybutton);
//        topPanel.add(playagainbutton);
        pcardPanel.add(playerlabel);
        dcardPanel.add(dealerlabel);

        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(dcardPanel,BorderLayout.CENTER);
        add(pcardPanel,BorderLayout.SOUTH);

    }//end BlackjackGUI


    /*************************************************************
     Shows the screen
     *************************************************************/
    public void display()
    {
        JFrame myFrame = new JFrame("BlackJack");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setContentPane(this);
        myFrame.setPreferredSize(new Dimension(700,550));

        //Display the window.
        myFrame.pack();
        myFrame.setVisible(true);

    }//end display

    /*************************************************************
     DealButton
     *************************************************************/
    class dealbutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            game = new Game();
            player = game.getPlayer();
            dealer = game.getDealer();

            winlosebox.setText("");

            dcardPanel.removeAll();
            pcardPanel.removeAll();

            dcardPanel.add(dealerlabel);
            pcardPanel.add(playerlabel);

            /**
             Get's dealer and player cards from Hand
             and the image associated with that random
             card and puts them on the screen.
             */

            ImageIcon temp = new ImageIcon(this.getClass().getResource("graphics/back.jpg"));
            dealercard0 = new JLabel(temp);

            //to iterate set and get current dealer cards

            CardGUI cardGUI = null;

            for (int x = 0; x < 2; x++) {
                cardGUI = deckGUI.getCard(dealer.getCardAt(x));
                if (x == 1) {
                    dealercard1 = new JLabel(cardGUI.getimage());
                }
                else {
                    dealercard2 = new JLabel(cardGUI.getimage());
                }
            }

            //to iterate set and get current player cards
            for (int x = 0; x < 2; x++) {
                cardGUI = deckGUI.getCard(player.getCardAt(x));
                if (x == 0) {
                    playercard1 = new JLabel(cardGUI.getimage());
                }
                else {
                    playercard2 = new JLabel(cardGUI.getimage());
                }
            }

            dcardPanel.add(dealercard0);
            dcardPanel.add(dealercard2);

            pcardPanel.add(playercard1);
            pcardPanel.add(playercard2);

            dealerlabel.setText("  Dealer:  "+ game.getDealerUpCardValue());
            playerlabel.setText("  Player:  " + game.getPlayerHandValue());

            nextbutton.setEnabled(true);
//            staybutton.setEnabled(true);
            dealbutton.setEnabled(false);

            if(game.blackjack())
            {
                nextbutton.setEnabled(false);
//                staybutton.setEnabled(false);
                dealbutton.setEnabled(true);
//                playagainbutton.setEnabled(true);
                winlosebox.setText("BlackJack");
            }

            add(dcardPanel,BorderLayout.CENTER);
            add(pcardPanel,BorderLayout.SOUTH);

            dcardPanel.repaint();
            dcardPanel.revalidate();
            pcardPanel.repaint();
            pcardPanel.revalidate();
        }
    }//end dealbutton

    /*************************************************************
     NextButtonutton
     Continues the A.I. visual play
     *************************************************************/
    class nextbutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int result = qlearning.testGUI(game);

            if (result == 1) {

                CardGUI hitcard = deckGUI.getCard(player.getLastCard());

                pcardPanel.removeAll();
                playerlabel.setText("  Player hits, new value:  " + game.getPlayerHandValue());
                pcardPanel.add(playerlabel);

                for (int x = 0; x < player.getHand().size(); x++) {
                    playercardhit = new JLabel(deckGUI.getCard(player.getCardAt(x)).getimage());
                    pcardPanel.add(playercardhit);
                }

                pcardPanel.repaint();
                pcardPanel.revalidate();

                if (game.getScore() == -1) {
                    winlosebox.setText("Bust");
                    nextbutton.setEnabled(false);
                    dealbutton.setEnabled(true);
                    playerlabel.setText("  Player:   " + player.getHandValue());
                    dealerlabel.setText(" Dealer:   " + dealer.getHandValue());
                }

            }

            else {
                playerlabel.setText("  Player stays at:   " + player.getHandValue());
                dcardPanel.removeAll();

                dcardPanel.add(dealerlabel);
                dealerlabel.setText(" Dealer ends with:   " + dealer.getHandValue());

                for (int x = 0; x < dealer.getHand().size(); x++) {
                    dealercard1 = new JLabel(deckGUI.getCard(dealer.getCardAt(x)).getimage());
                    dcardPanel.add(dealercard1);
                }

                dcardPanel.repaint();
                dcardPanel.revalidate();

                if (dealer.getHandValue() > 21) {
                    winlosebox.setText("Dealer busts");
                }
                else if (game.getScore() == -1) {
                    winlosebox.setText("Dealer wins");
                }
                else if (game.getScore() == 0) {
                    winlosebox.setText("DRAW");
                }
                else {
                    winlosebox.setText("Dealer loses");
                }
                nextbutton.setEnabled(false);
                dealbutton.setEnabled(true);

//                for (int x = 0; x < 2; x++) {
//                    cardGUI = deckGUI.getCard(dealer.getCardAt(x));
//                    if (x == 1) {
//                        dealercard1 = new JLabel(cardGUI.getimage());
//                    }
//                    else {
//                        dealercard2 = new JLabel(cardGUI.getimage());
//                    }
//                }

//                dcardPanel.remove(dealercard0);
//                dcardPanel.add(dealercard1);
//
//            dealer = game.dealerPlays();
//            dcardPanel.removeAll();
//            dcardPanel.add(dealerlabel);
//            dealerlabel.setText(" " + dealerlabel.getText());
//
//            //iterate through cards and re-display
//            CardGUI dhitcard = null;
//            Iterator<CardGUI> scan = (dealer.inHand).iterator();
//            while (scan.hasNext())
//            {
//                dhitcard = scan.next();
//                dealercardhit = new JLabel(dhitcard.getimage());
//                dcardPanel.add(dealercardhit);
//            }
//
//            dealerlabel.setText("Dealer: " + game.handValue(dealer));
//            playerlabel.setText("Player: " + game.handValue(player));
//
//            winlosebox.setText(game.winner());
//            nextbutton.setEnabled(false);
//            staybutton.setEnabled(false);
//
//            playagainbutton.setEnabled(true);
            }

        }
    }//end nextbutton

    /*************************************************************
     StayButton
     dealer must hit on 16 or lower. determines the winner,
     player wins if under 21 and above dealer.
     Tie goes to dealer.
     @param e Stay button pressed
     *************************************************************/
//    class staybutton implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//
//            dcardPanel.remove(dealercard0);
//            dcardPanel.add(dealercard1);
//
//            dealer = game.dealerPlays();
//            dcardPanel.removeAll();
//            dcardPanel.add(dealerlabel);
//            dealerlabel.setText(" " + dealerlabel.getText());
//
//            //iterate through cards and re-display
//            CardGUI dhitcard = null;
//            Iterator<CardGUI> scan = (dealer.inHand).iterator();
//            while (scan.hasNext())
//            {
//                dhitcard = scan.next();
//                dealercardhit = new JLabel(dhitcard.getimage());
//                dcardPanel.add(dealercardhit);
//            }
//
//            dealerlabel.setText("Dealer: " + game.handValue(dealer));
//            playerlabel.setText("Player: " + game.handValue(player));
//
//            winlosebox.setText(game.winner());
//            nextbutton.setEnabled(false);
//            staybutton.setEnabled(false);
//
//            playagainbutton.setEnabled(true);
//
//        }
//    }//end staybutton

    /*************************************************************
     PlayAgainButton
     resets screen
     @param e Play Again button pressed
     *************************************************************/
//    class playagainbutton implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//
//            dealerlabel.setText("Dealer: ");
//            playerlabel.setText("Player: ");
//            winlosebox.setText("");
//            dealer = new Hand();
//            player = new Hand();
//            game=new Blackjack(dealer, player);
//
//            dcardPanel.removeAll();
//            pcardPanel.removeAll();
//
//            nextbutton.setEnabled(false);
//            staybutton.setEnabled(false);
//            playagainbutton.setEnabled(false);
//            dealbutton.setEnabled(true);
//
//        }
//    }//end playagainbutton
}//end BlackjackGUI
