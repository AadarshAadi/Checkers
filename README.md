# Android Checkers Game

This is a simple implementation of the Checkers game for Android using Java. The game features a basic 8x8 checkers board where players can move pieces according to traditional checkers rules.

## Features

- Traditional 8x8 checkers board.
- Two players: RED and BLUE.
- Basic piece movement with capture mechanics.
- Pieces are converted to Kings upon reaching the opponent's end of the board.
- Simple and intuitive UI.

## Installation

1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.

## Code Overview

### Main.java

This is the main activity of the app, which sets up the game board and handles piece movement and game logic.

- **Variables:**
  - `Button[][] b1`: 2D array of buttons representing the checkers board.
  - `Button selectedButton`: The currently selected button.
  - `int selectedI, selectedJ`: Coordinates of the selected button.
  - `Button start`: Start button to initialize the game.

- **Methods:**
  - `onCreate()`: Initializes the start button and sets its onClickListener to call `main_stuff()`.
  - `main_stuff()`: Sets up the checkers board and initializes button listeners for each cell.
  - `checkStriker()`: Checks if a button contains a piece.
  - `checkColor()`: Checks the color of the piece on a button.
  - `redMovement()`: Handles movement logic for red pieces.
  - `blueMovement()`: Handles movement logic for blue pieces.
  - `checkCaptureMove()`: Checks if a capture move is valid.
  - `checkSimpleMove()`: Checks if a simple move is valid.
  - `movePiece()`: Moves a piece from one cell to another.
  - `checkPositionAvailable()`: Checks if a position is available for a move.
  - `convertToKing()`: Converts a piece to a King.
  - `closeApp()`: Closes the app.

### Layout Files

- **activity_main2.xml**: Contains the layout for the start screen.
- **activity_main.xml**: Contains the layout for the checkers board.

## How to Play

1. Launch the app.
2. Press the start button to initialize the game board.
3. Tap on a piece to select it.
4. Tap on a valid destination to move the selected piece.
5. Capture opponent pieces by jumping over them.
6. Reach the opponent's end of the board to convert your piece to a King.
7. The game continues until one player captures all the opponent's pieces or no valid moves are left.

## Contributing

If you'd like to contribute to the project, feel free to fork the repository and submit a pull request. All contributions are welcome!
