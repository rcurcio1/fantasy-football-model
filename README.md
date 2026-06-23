# Fantasy Football Smart Draft

ML algorithm utilizing Q-Learning to train a model for drafting fantasy players. The state of the model is defined by the positions of the players on the roster of the agent drafting. These states are represented by a string, with each character representing the position of a player on the roster.

## Before Running
The players should be represented in the root of the repository in a file named `players.csv`. Each row of the CSV should have a player's name, their position (D, K, QB, RB, TE, or WR), and their projection which can be represented as a double.