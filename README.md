Dodge Drag Racer Game

A text-based Java console game where you drag race a Dodge Challenger against the computer’s Dodge Charger in a quarter-mile showdown.
Each race is determined by dice rolls, betting, and a little randomness for reaction times.
--------------------------------------------------------------------------------------------------------------------------------------------
How to Run

Open the project in IntelliJ IDEA (or any Java IDE).

Place all .java files (Main.java, Vehicle.java, Challenger.java, Charger.java, DodgeCar.java, Trim.java, Draggable.java) in the same src/ folder.

Compile and run Main.java.
-----------------------------------------------------------------------------------------------------------------------------------------------
Game Rules

Starting Money:

Player: $5000

Computer: $5000

Car: Player always drives a Challenger. Computer drives a Charger.

Trims Available:

SXT – Base trim, slowest

R/T – Mid-performance trim

Hellcat – Supercharged, very fast

Demon – Drag-strip monster, ultimate gamble

Racing Mechanics

Dice Rolls:

Each racer rolls a 6-sided die twice.

Each roll maps to time:

Roll 1 = 10 seconds

Roll 2 = 20 seconds

Roll 6 = 60 seconds

Total base time = (Roll1 + Roll2) × 10.
===========================================================================================================================================================
Trim Offsets: Faster trims (Hellcat, Demon) reduce time, slower trims (SXT, R/T) add time.

Reaction Jitter: Random small delay (0.1 – 0.5 seconds).
============================================================================================================================================================
Winner: The racer with the lowest total time wins.
====================================================================================================================================
Betting System
======================================================================================================================================================
Before each race, the player chooses a bet amount.
=========================================================================================================
Winner takes the pot (money is transferred between player and computer).
==========================================================================================================
Bets must be valid (cannot exceed either side’s current money).
=====================================================================================================================================================
All or Nothing Rule
==========================================================================================================================================
If the player loses all money but still has their Challenger:
---------------------------------------------------------------------------------------------------------------------------------------------------
Player enters an ALL OR NOTHING RACE.

Bets their car against the computer’s $5000.

Outcomes:
======================================================
Win: Player regains their Challenger + $10,000 cash.
===================================================================
Lose: Player loses everything → $0 and no car → ULTIMATE LOSER 💀.
=======================================================================
End of Game

The game ends if:

Player quits, OR

Player becomes Ultimate Loser (no money, no car).
