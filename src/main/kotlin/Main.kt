/**
 * @author charlizeaponte
 * @date 1/24/23
 * Assignment 1: A connect four game on a six by six board
 */

import java.util.Scanner


val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
 println("Welcome to Connect Four!")


 // Initialize your game board and other necessary variables
 val FIR_board = FourInARow()
 var userInput: String
 //Set Player numbers
 val HUMAN_PLAYER: Int = GameConstants.BLUE
 val COMPUTER_PLAYER: Int = GameConstants.RED
 val scanner = Scanner(System.`in`)

 // Game loop
 do {
  FIR_board.printBoard()


  // TODO: 1- Accept user move
  //Tells player to set position/ move on the board
  println("Select a position on the board by entering a number from 0 to 35:")
  var userInput = scanner.nextLine()
  var userChoice = userInput.toInt()
  //if the move that the user inputs is within the range of the board and not taken by the computer set the move
  if (userChoice in 0..35&& FIR_board.getInputs(userChoice) != GameConstants.RED) {
   FIR_board.setMove(HUMAN_PLAYER,userChoice)
   FIR_board.printBoard()
  }else {
   // if user input is not in range or taken by the computer have them reenter the
   println("YOU ENTERED AN INVALID POSITION. Please ENTER A NUMBER FROM 0 TO 35!!")
   userInput = scanner.nextLine()
   userChoice = userInput.toInt()
   FIR_board.setMove(HUMAN_PLAYER,userChoice)
   FIR_board.printBoard()
  }

  // TODO: 2- Call getComputerMove
  val computerMove = FIR_board.computerMove
  FIR_board.printBoard()
  println("This is computer move!!")

  // TODO: 3- Check for winner
  val currentState = FIR_board.checkForWinner()

  // TODO: 4- Print game end messages in case of Win, Lose, or Tie
  when (currentState) {
   GameConstants.BLUE_WON -> println("Congratulations! Blue player wins!")
   GameConstants.RED_WON -> println("Congratulations! Red player wins!")
   GameConstants.TIE -> println("It's a tie! The game is over.")
  }


 }while (currentState == GameConstants.PLAYING && userInput != "q")

 // Print game over message
 println("Game Over!")

 // Tell player to quit the game
 println("Please q to Quit!")
userInput = scanner.nextLine().toLowerCase()


// Print goodbye message for player
println("Thanks for playing! Goodbye.")
}