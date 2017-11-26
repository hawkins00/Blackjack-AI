# Blackjack
Blackjack A.I. implemented with reinforcement learning
This program was a group Project for CS541 at Portland State University
Fall of 2017
The Group Members are: Joshua Sander, Alex Davis, Matt Hawkins, Scott Jones, Dakota Sanchez, Daniel Corcoran

Rules for Our Simple BlackJack Game

1. Played with 6 decks of 52 cards
2. There is one player at a time heads up with one dealer. 
2. Aces may be counted as 1 or 11 points, 2 to 9 according to pip value, tens and face cards count as ten
3. The value of a hand is the sum of the point valued of the individual cards, except BlackJack, which is the highest hand, consisting of any 10 point card, and it outranks all other 21 point hands.
4. All bets will be be equal and there is only one (implicit) round of betting (before the deal).
5. After the player has bet, the dealer will give two cards to the player and two cards to himself. One of the dealer cards is face up. The face down card is called the "hole card".
6. There is no "insurance" betting allowed. 
7. If the dealer has a blackjack he will show it immediately and all wagers will lose unless the player also has a blackjack, in which case the result is a push. 
8. The player then has a choice of moves: Stand or hit (At this time we do not allow the player to split or double his bet, or surrender half his bet to get out of the hand).
	Stand: Player ends his turn and does nothing. 
	Hit: Player draws another card. If this card causes the point total to exceed 21 ("bust"), then he looses. If the player doesn't bust, he must choose again. 
9. After the player's turn, the dealer will turn over his hole card. If the dealer has 16 or less, then he will draw another card. A special situation is when the dealer has an ace and any number of cards totaling six points (known as a "soft 17"). In our game the dealer will NOT hit on a soft 17.
10. If the dealer exceeds 21 points, then the player wins. 
11. If the dealer does not bust, then the higher point total wins. 
12. All wages pay even money (one point), including a blackjack. 
