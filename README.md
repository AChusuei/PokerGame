Texas Hold'Em Poker Hand Calculator 
=========
This application is a simple poker hand comparison and analyzer. It was a project to just figure out the algorithm
that determines exactly the highest hand that a player holds when playing Texas Hold 'Em.

For those not in the know, TexasHoldEm is a variant of poker where two or more players compete 
against each other to create the best winning poker hand. Each player has two cards, and both use 
a community of five cards laid out on the table. There are betting rounds in one round of the game, 
but this program is primarily concerned with calculating the final best hand that a player has once all 
five of the community cards are played. This is done for each player by taking the two cards from 
each player's hand, combining it with the five community cards, and figuring out the highest possible five-card
poker hand combination from the seven total cards. The player with the best poker hand from the combination of 
their two cards plus the shared community cards wins.

For more information, go here: http://www.pokerlistings.com/strategy/beginner/how-to-determine-the-winning-hand

Running the program
=========
In this variant, I just have two players for simplicity.

It's an eclipse project, just simply import the folder as a project.

To run from the command line prompt, compile the project, then go to the bin directory, 
and run the game using this syntax:

java com.cards.TexasHoldEmGame <playerOneHand> <playerTwoHand> <communityBoard>

All three parameters are a comma delimited list of cards. Each player should have two cards, and the 
community board should hae five. (If you change the number of cards, I can't promise there won't be errors!)

Each card is represented by two characters.
The first character is the rank, and can either be "A","K","Q","J","T"(ten),"9","8","7","6","5","4","3","2"
The second is the suit, and can be either "s"(Spades), "c"(Clubs), "h"(Hearts), or "d" diamonds
e.g. therefore, "5c" is the five of clubs, "Ad" is the Ace of diamonds

A sample file that runs a few scenarios can be run from runGames.bat in the bin directory, 
but you'll need to make sure that java.exe is on your path.

From Eclipse, one can also run the game using run configurations, but those are a little more annoying to set up.
