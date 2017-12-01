package src.project;

//************************************************************
//
//  DeckGUI.Java             Authors:  Lewis, Chase, Coleman
//
//  Provides an implementation of a deck of cards using a
//  set to represent the cards
//
//************************************************************


//import graphics;

import java.util.Random;
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class DeckGUI
{

    ArrayList<CardGUI> deckSet = new ArrayList<CardGUI>();

    /**********************************************************
     Constructs a deck of 52 CardGUIs and
     puts them in the set.
     **********************************************************/
    public DeckGUI()
    {
        ImageIcon card49 = new ImageIcon(this.getClass().getResource("graphics/2s.jpg"));
        CardGUI twos = new CardGUI(card49, 2,"spade", "Two");
        ImageIcon card45 = new ImageIcon(this.getClass().getResource("graphics/3s.jpg"));
        CardGUI threes = new CardGUI(card45, 3,"spade", "Three");
        ImageIcon card1 = new ImageIcon(this.getClass().getResource("graphics/4s.jpg"));
        CardGUI fours = new CardGUI(card1, 4, "spade","Four");
        ImageIcon card13 = new ImageIcon(this.getClass().getResource("graphics/5s.jpg"));
        CardGUI fives = new CardGUI(card13, 5, "spade", "Five");
        ImageIcon card27 = new ImageIcon(this.getClass().getResource("graphics/6s.jpg"));
        CardGUI sixs = new CardGUI(card27, 6, "spade", "Six");
        ImageIcon card9 = new ImageIcon(this.getClass().getResource("graphics/7s.jpg"));
        CardGUI sevens = new CardGUI(card9, 7, "spade" , "Seven");
        ImageIcon card17 = new ImageIcon(this.getClass().getResource("graphics/8s.jpg"));
        CardGUI eights = new CardGUI(card17, 8, "spade", "Eight");
        ImageIcon card40 = new ImageIcon(this.getClass().getResource("graphics/9s.jpg"));
        CardGUI nines = new CardGUI(card40, 9,"spade", "Nine");
        ImageIcon card50 = new ImageIcon(this.getClass().getResource("graphics/10s.jpg"));
        CardGUI tens = new CardGUI(card50, 10,"spade", "Ten");
        ImageIcon card26 = new ImageIcon(this.getClass().getResource("graphics/jacks.jpg"));
        CardGUI jacks = new CardGUI(card26, 10, "spade", "Jack");
        ImageIcon card33 = new ImageIcon(this.getClass().getResource("graphics/queens.jpg"));
        CardGUI queens = new CardGUI(card33, 10,"spade", "Queen");
        ImageIcon card18 = new ImageIcon(this.getClass().getResource("graphics/kings.jpg"));
        CardGUI kings = new CardGUI(card18, 10, "spade", "King");
        ImageIcon card15 = new ImageIcon(this.getClass().getResource("graphics/aces.jpg"));
        CardGUI aces = new CardGUI(card15, 11, "spade", "Ace");

        ImageIcon card39 = new ImageIcon(this.getClass().getResource("graphics/2h.jpg"));
        CardGUI twoh = new CardGUI(card39, 2,"heart", "Two");
        ImageIcon card2 = new ImageIcon(this.getClass().getResource("graphics/3h.jpg"));
        CardGUI threeh = new CardGUI(card2, 3, "heart","Three");
        ImageIcon card8 = new ImageIcon(this.getClass().getResource("graphics/4h.jpg"));
        CardGUI fourh = new CardGUI(card8, 4, "heart", "Four");
        ImageIcon card51 = new ImageIcon(this.getClass().getResource("graphics/5h.jpg"));
        CardGUI fiveh = new CardGUI(card51, 5,"heart", "Five");
        ImageIcon card24 = new ImageIcon(this.getClass().getResource("graphics/6h.jpg"));
        CardGUI sixh = new CardGUI(card24, 6,"heart", "Six");
        ImageIcon card34 = new ImageIcon(this.getClass().getResource("graphics/7h.jpg"));
        CardGUI sevenh = new CardGUI(card34, 7,"heart", "Seven");
        ImageIcon card35 = new ImageIcon(this.getClass().getResource("graphics/8h.jpg"));
        CardGUI eighth = new CardGUI(card35, 8,"heart", "Eight");
        ImageIcon card4 = new ImageIcon(this.getClass().getResource("graphics/9h.jpg"));
        CardGUI nineh = new CardGUI(card4, 9, "heart","Nine");
        ImageIcon card7 = new ImageIcon(this.getClass().getResource("graphics/10h.jpg"));
        CardGUI tenh = new CardGUI(card7, 10,"heart", "Ten");
        ImageIcon card3 = new ImageIcon(this.getClass().getResource("graphics/jackh.jpg"));
        CardGUI jackh = new CardGUI(card3, 10, "heart","Jack");
        ImageIcon card25 = new ImageIcon(this.getClass().getResource("graphics/queenh.jpg"));
        CardGUI queenh = new CardGUI(card25, 10,"heart", "Queen");
        ImageIcon card36 = new ImageIcon(this.getClass().getResource("graphics/kingh.jpg"));
        CardGUI kingh = new CardGUI(card36, 10,"heart", "King");
        ImageIcon card10 = new ImageIcon(this.getClass().getResource("graphics/aceh.jpg"));
        CardGUI aceh = new CardGUI(card10, 11, "heart", "Ace");

        ImageIcon card31 = new ImageIcon(this.getClass().getResource("graphics/2d.jpg"));
        CardGUI twod = new CardGUI(card31, 2,"diamond", "Two");
        ImageIcon card30 = new ImageIcon(this.getClass().getResource("graphics/3d.jpg"));
        CardGUI threed = new CardGUI(card30, 3,"diamond", "Three");
        ImageIcon card32 = new ImageIcon(this.getClass().getResource("graphics/4d.jpg"));
        CardGUI fourd = new CardGUI(card32, 4,"diamond", "Four");
        ImageIcon card48 = new ImageIcon(this.getClass().getResource("graphics/5d.jpg"));
        CardGUI fived = new CardGUI(card48, 5,"diamond", "Five");
        ImageIcon card5 = new ImageIcon(this.getClass().getResource("graphics/6d.jpg"));
        CardGUI sixd = new CardGUI(card5, 6, "diamond", "Six");
        ImageIcon card41 = new ImageIcon(this.getClass().getResource("graphics/7d.jpg"));
        CardGUI sevend = new CardGUI(card41, 7,"diamond", "Seven ");
        ImageIcon card14 = new ImageIcon(this.getClass().getResource("graphics/8d.jpg"));
        CardGUI eightd = new CardGUI(card14, 8, "diamond", "Eight");
        ImageIcon card16 = new ImageIcon(this.getClass().getResource("graphics/9d.jpg"));
        CardGUI nined = new CardGUI(card16, 9, "diamond", "Nine");
        ImageIcon card12 = new ImageIcon(this.getClass().getResource("graphics/10d.jpg"));
        CardGUI tend = new CardGUI(card12, 10, "diamond", "Ten");
        ImageIcon card11 = new ImageIcon(this.getClass().getResource("graphics/jackd.jpg"));
        CardGUI jackd = new CardGUI(card11, 10, "diamond", "Jack");
        ImageIcon card6 = new ImageIcon(this.getClass().getResource("graphics/queend.jpg"));
        CardGUI queend = new CardGUI(card6, 10, "diamond", "Queen");
        ImageIcon card47 = new ImageIcon(this.getClass().getResource("graphics/kingd.jpg"));
        CardGUI kingd = new CardGUI(card47, 10,"diamond", "King");
        ImageIcon card20 = new ImageIcon(this.getClass().getResource("graphics/aced.jpg"));
        CardGUI aced = new CardGUI(card20, 11,"diamond", "Ace");

        ImageIcon card28 = new ImageIcon(this.getClass().getResource("graphics/2c.jpg"));
        CardGUI twoc = new CardGUI(card28, 2,"club", "Two");
        ImageIcon card38 = new ImageIcon(this.getClass().getResource("graphics/3c.jpg"));
        CardGUI threec = new CardGUI(card38, 3,"club", "Three");
        ImageIcon card19 = new ImageIcon(this.getClass().getResource("graphics/4c.jpg"));
        CardGUI fourc = new CardGUI(card19, 4,"club", "Four");
        ImageIcon card21 = new ImageIcon(this.getClass().getResource("graphics/5c.jpg"));
        CardGUI fivec = new CardGUI(card21, 5,"club", "Five");
        ImageIcon card43 = new ImageIcon(this.getClass().getResource("graphics/6c.jpg"));
        CardGUI sixc = new CardGUI(card43, 6,"club", "Six");
        ImageIcon card42 = new ImageIcon(this.getClass().getResource("graphics/7c.jpg"));
        CardGUI sevenc = new CardGUI(card42, 7,"club", "Seven");
        ImageIcon card22 = new ImageIcon(this.getClass().getResource("graphics/8c.jpg"));
        CardGUI eightc = new CardGUI(card22, 8,"club", "Eight");
        ImageIcon card23 = new ImageIcon(this.getClass().getResource("graphics/9c.jpg"));
        CardGUI ninec = new CardGUI(card23, 9,"club", "Nine");
        ImageIcon card44 = new ImageIcon(this.getClass().getResource("graphics/10c.jpg"));
        CardGUI tenc = new CardGUI(card44, 10,"club", "Ten");
        ImageIcon card29 = new ImageIcon(this.getClass().getResource("graphics/jackc.jpg"));
        CardGUI jackc = new CardGUI(card29, 10,"club", "Jack");
        ImageIcon card52 = new ImageIcon(this.getClass().getResource("graphics/queenc.jpg"));
        CardGUI queenc = new CardGUI(card52, 10,"club", "Queen");
        ImageIcon card46 = new ImageIcon(this.getClass().getResource("graphics/kingc.jpg"));
        CardGUI kingc = new CardGUI(card46, 10,"club", "King");
        ImageIcon card37 = new ImageIcon(this.getClass().getResource("graphics/acec.jpg"));
        CardGUI acec = new CardGUI(card37, 11,"club", "Ace");


        deckSet.add(acec);
        deckSet.add(threeh);
        deckSet.add(aces);
        deckSet.add(jackh);
        deckSet.add(nineh);
        deckSet.add(sixd);
        deckSet.add(queend);
        deckSet.add(aceh);
        deckSet.add(nined);
        deckSet.add(fours);
        deckSet.add(fourh);
        deckSet.add(aced);
        deckSet.add(sevens);
        deckSet.add(tenh);
        deckSet.add(jackd);
        deckSet.add(tend);
        deckSet.add(fives);
        deckSet.add(eightd);
        deckSet.add(eights);
        deckSet.add(kings);
        deckSet.add(fourc);
        deckSet.add(fivec);
        deckSet.add(eightc);
        deckSet.add(ninec);
        deckSet.add(sixh);
        deckSet.add(queenh);
        deckSet.add(jacks);
        deckSet.add(sixs);
        deckSet.add(twoc);
        deckSet.add(jackc);
        deckSet.add(threed);
        deckSet.add(twod);
        deckSet.add(fourd);
        deckSet.add(queens);
        deckSet.add(sevenh);
        deckSet.add(eighth);
        deckSet.add(kingh);
        deckSet.add(threec);
        deckSet.add(twoh);
        deckSet.add(nines);
        deckSet.add(sevend);
        deckSet.add(sevenc);
        deckSet.add(sixc);
        deckSet.add(tenc);
        deckSet.add(threes);
        deckSet.add(kingc);
        deckSet.add(kingd);
        deckSet.add(fived);
        deckSet.add(twos);
        deckSet.add(tens);
        deckSet.add(fiveh);
        deckSet.add(queenc);


    }

    public String getFace(Card toRank) {

        switch(toRank.getRank()) {
            case ACE:
                return "Ace";
            case KING:
                return "King";
            case QUEEN:
                return "Queen";
            case JACK:
                return "Jack";
            case TEN:
                return "Ten";
            case NINE:
                return "Nine";
            case EIGHT:
                return "Eight";
            case SEVEN:
                return "Seven";
            case SIX:
                return "Six";
            case FIVE:
                return "Five";
            case FOUR:
                return "Four";
            case THREE:
                return "Three";
            case TWO:
                return "Two";
        }

        return null;
    }

    public String getSuit(Card toSuit) {
        switch(toSuit.getSuit()) {
            case CLUBS:
                return "club";
            case DIAMONDS:
                return "diamond";
            case HEARTS:
                return "heart";
            case SPADES:
                return "spade";
        }

        return null;
    }

    public CardGUI getCard(Card toFind) {

        String myFace = getFace(toFind);
        String mySuit = getSuit(toFind);

        for (CardGUI cardGUI: deckSet) {
            if (cardGUI.getface().equals(myFace)) {
                if (cardGUI.getsuit().equals(mySuit)) {
                    return cardGUI;
                }
            }
        }

        return null;
    }


    /**********************************************************
     returns a single random CardGUI from the deck
     **********************************************************/
//    public CardGUI getCardGUI()
//    {
//        CardGUI result = new CardGUI();
//        result = deckSet.removeRandom();
//
//        return result;
//    }

}//end deck











