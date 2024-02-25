package edu.quinnipiac.ser210.connectfour

import kotlin.random.Random

class FourInARow : IGame {



    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS){0} }

    //function to get the positions of the computer and user inputs from the board
    fun getInputs(location: Int): Int {
        val row = location / GameConstants.COLS
        val col = location % GameConstants.COLS
        return board[row][col]
    }

    override fun clearBoard() {
        // TODO Auto-generated method stub
        //for loop to clear game board
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                board[row][col] = GameConstants.EMPTY
            }
        }
    }


    override fun setMove(player: Int, location: Int) {
        // Setting player to the correct location on the board
        val rows = location / 6
        val cols = location % 6
        board[rows][cols] = player


    }


    override val computerMove: Int
        get() {
            // Iterate through each column to find the best move
            for (col in 0 until GameConstants.COLS) {
                // Check if the column is not full
                if (board[0][col] == GameConstants.EMPTY) {
                    // Simulate placing a piece in this column and check if it leads to a win
                    for (row in GameConstants.ROWS - 1 downTo 0) {
                        if (board[row][col] == GameConstants.EMPTY) {
                            // Try placing a piece for the computer
                            board[row][col] = GameConstants.RED
                            // Check if this move results in a win
                            val result = checkForWinner()
                            // Undo the move
                            board[row][col] = GameConstants.EMPTY
                            // If this move results in a win for the computer, return the column
                            if (result == GameConstants.RED_WON) {
                                return col
                            }
                            // Check if this move blocks the opponent from winning
                            board[row][col] = GameConstants.BLUE
                            val opponentResult = checkForWinner()
                            // Undo the move
                            board[row][col] = GameConstants.EMPTY
                            // If this move blocks the opponent from winning, prioritize it
                            if (opponentResult == GameConstants.BLUE_WON) {
                                return col
                            }
                            // If no immediate win or block, continue searching
                            break
                        }
                    }
                }
            }
            // If no immediate win or block is found, make a random move
            return Random.nextInt(GameConstants.COLS)
        }




    override fun checkForWinner(): Int {
        // Check for a winner horizontally, vertically, and diagonally
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                val player = board[row][col]

                // Check horizontally
                if (col + 3 < GameConstants.COLS &&
                    player != GameConstants.EMPTY &&
                    player == board[row][col + 1] &&
                    player == board[row][col + 2] &&
                    player == board[row][col + 3]
                ) {
                    return if (player == GameConstants.BLUE) GameConstants.BLUE_WON else GameConstants.RED_WON
                }

                // Check vertically
                if (row + 3 < GameConstants.ROWS &&
                    player != GameConstants.EMPTY &&
                    player == board[row + 1][col] &&
                    player == board[row + 2][col] &&
                    player == board[row + 3][col]
                ) {
                    return if (player == GameConstants.BLUE) GameConstants.BLUE_WON else GameConstants.RED_WON
                }
                // Check diagonally from top-left to bottom-right
                if (row + 3 < GameConstants.ROWS && col + 3 < GameConstants.COLS &&
                    player != GameConstants.EMPTY &&
                    player == board[row + 1][col + 1] &&
                    player == board[row + 2][col + 2] &&
                    player == board[row + 3][col + 3]
                ) {
                    return if (player == GameConstants.BLUE) GameConstants.BLUE_WON else GameConstants.RED_WON
                }

                // Check diagonally from top-right to bottom-left
                if (row + 3 < GameConstants.ROWS && col - 3 >= 0 &&
                    player != GameConstants.EMPTY &&
                    player == board[row + 1][col - 1] &&
                    player == board[row + 2][col - 2] &&
                    player == board[row + 3][col - 3]
                ) {
                    return if (player == GameConstants.BLUE) GameConstants.BLUE_WON else GameConstants.RED_WON
                }
            }
        }

        // Check for a tie
        var isBoardFull = true
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == GameConstants.EMPTY) {
                    isBoardFull = false
                    break
                }
            }
            if (!isBoardFull) {
                break
            }
        }

        if (isBoardFull) {
            return GameConstants.TIE
        }

        // If no winner is found, return PLAYING
        return GameConstants.PLAYING
    }

    /**
     * Print the game board
     */
    fun printBoard() {
        // Print top border
        println("+" + "-".repeat(GameConstants.COLS * 3 + 5) + "+")

        for (row in 0 until GameConstants.ROWS) {
            print("|") // left border
            for (col in 0 until GameConstants.COLS) {
                printCell(board[row][col]) // print each of the cells
                if (col != GameConstants.COLS - 1) {
                    print("|") // print vertical partition
                }
            }
            println("|") // right border

            if (row != GameConstants.ROWS - 1) {
                println("|" + "-".repeat(GameConstants.COLS * 3 + 5) + "|") // print horizontal partition
            }
        }

        // Print bottom border
        println("+" + "-".repeat(GameConstants.COLS * 3 + 5) + "+")
        println()
    }

    /**
     * Print a cell with the specified "content"
     * @param content either BLUE, RED or EMPTY
     */
    fun printCell(content: Int) {
        when (content) {
            GameConstants.EMPTY -> print("   ")
            GameConstants.BLUE -> print(" B ")
            GameConstants.RED -> print(" R ")
        }
    }
}