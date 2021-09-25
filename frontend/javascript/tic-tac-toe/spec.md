# Tic-Tac-Toe

## Description

- Tic-tac-toe is a game that involves two players, a board, and turns.
- Player 1 and Player 2 take turns placing X's and O's on a board that is sectioned into a 9-cell grid
- The game starts with the board's state initially clear, no X's or 0's.
- Player 1's turn is first. Player 2 is next .
- After Player 1 places an X on the board, it is now Player 2's turn to place an O on the board.
- Player 2 can place an O on any cell on the board as long as the cell is not already occupied by another X or O.
- After each Player's turn, we check for an outcome of the players' actions
- There can be three outcomes to the game:
  - Player 2 has won the game
  - Player 2 wins and Player 1 loses
  - or Player 1 and Player 2 draw. A draw means that neither Player 1 nor Player 2 has won the game
- When an outcome exists, the game is finished, neighter Player 1 nor Player 2 can continue placing X's or O's on the board
- In order to play the game again, the board must be clear of all X's and O's

## UI

- 9-cell grid
  - styled divs inside of a game-container
- X and O
  - strings

## Features

- Undo state: an array of grid states

## Behavior/UX

- initialState: board initial state

  - board will be a 2-dimensional array that is initialized to empty
  - The game starts with the board's state initially clear, no X's or 0's.

- calculateTurn(): boolean that indicates player 1 or player 2's turn, derived from number of Xs and Os

  - Player 1's turn is first. Player 2 is next .
  - After Player 1 places an X on the board, it is now Player 2's turn to place an O on the board.

- canPlace(): boolean indicating whether or not a Player can place an X or O in the cell. This can be reflected in the DOM

  - Player 2 can place an O on any cell on the board as long as the cell is not already occupied by another X or O.

- checkOutcome(): called after every placement, responsible for performing side-effect of disabling board, displaying the outcome to the DOM

  - After each Player's turn, we check for an outcome of the players' actions
  - There can be three outcomes to the game:
    - either Player 1 wins and Player 2 loses
    - Player 2 wins and Player 1 loses
    - or Player 1 and Player 2 draw. A draw means that neither Player 1 nor Player 2 has won the game

- isFinalState(): calculate whether or not there is a winner

  - When an outcome exists, the game is finished, neighter Player 1 nor Player 2 can continue placing X's or O's on the board

- reset():
  - In order to play the game again, the board must be clear of all X's and O's
