package src.project;

    //************************************************************
//
//  Card.java             Authors:  Lewis, Chase, Coleman
//
//  Provides an implementation of a class to represent a
//  playing card
//
//************************************************************

import java.util.Random;
import javax.swing.*;


    public class CardGUI {

        protected String face;
        protected ImageIcon cardpic;
        protected int value;
        protected String suit;

        /***********************************************************
         Constructs a card.
         ***********************************************************/
        public CardGUI()
        {
            cardpic = null;
            value = 0;
            suit = null;
            face = null;
        }

        /***********************************************************
         Draws the shape.
         @param x the image of the card
         @param val the value of the card
         @param s the suit of the card
         @param f the type of the card
         ***********************************************************/
        public CardGUI(ImageIcon x, int val, String s, String f)
        {
            cardpic = x;
            value = val;
            face = f;
            suit = s;
        }

        /***********************************************************
         Returns the image.
         ***********************************************************/
        public ImageIcon getimage()
        {
            return cardpic;
        }

        /***********************************************************
         Returns the value.
         ***********************************************************/
        public int getvalue()
        {
            return value;
        }

        /***********************************************************
         Allows the user to set the value.
         @param v new value of card
         ***********************************************************/
        public void setvalue(int v)
        {
            value = v;
        }

        /***********************************************************
         Returns the suit
         ***********************************************************/
        public String getsuit()
        {
            return suit;
        }

        /***********************************************************
         Returs the face
         ***********************************************************/
        public String getface()
        {
            return face;
        }

        /***********************************************************
         Returs a string representing the card
         ***********************************************************/
        public String toString()
        {
            return "Face: "+ face + " Suit "+ suit +" Value: "+ value;
        }

    }//end Card
