package edu.quinnipiac.ser210.connectfour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), IGame {

    // Game board represented as a 2D array
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS) }

    // Initialize the board with empty cells
    init {
        clearBoard()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun clearBoard() {
        for (i in 0 until GameConstants.ROWS) {
            for (j in 0 until GameConstants.COLS) {
                board[i][j] = GameConstants.EMPTY
            }
        }
    }

    override fun setMove(player: Int, location: Int) {
        // Implement setting move logic here
    }

    override val computerMove: Int
        get() {
            // Implement computer move logic here
            return 0 // Placeholder, replace with actual implementation
        }

    override fun checkForWinner(): Int {
        // Implement winner checking logic here
        return GameConstants.PLAYING // Placeholder, replace with actual implementation
    }
}
