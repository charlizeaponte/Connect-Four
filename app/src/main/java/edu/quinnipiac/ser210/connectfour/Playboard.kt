package edu.quinnipiac.ser210.connectfour

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.os.Handler



class Playboard : Fragment() {

    var FourInARow_board = FourInARow()

    private val handler = Handler()
    private val computerMoveDelayMillis = 2000L
    private var gameEnded = false
    private lateinit var gameConditionsTextView: TextView
    private lateinit var resetButton: Button


    val computerMoveRunnable = Runnable {
        // Set the text indicating it's the COMPUTER's turn
        view?.findViewById<TextView>(R.id.gameTurns)?.text = "Computer's Turn"

        // Filter out the buttons that are not selected by the user
        val availableButtons = fabButtons.filter { it !in userSelectedButtons }
        // Check if there are any available buttons left
        if (availableButtons.isNotEmpty()) {

            // Set the text indicating it's the player's turn
            view?.findViewById<TextView>(R.id.gameTurns)?.text = "Player's Turn"

            // Get a random index to select a FloatingActionButton from available buttons
            val randomIndex = (0 until availableButtons.size).random()
            // Get the selected FloatingActionButton
            val selectedFab = availableButtons[randomIndex]
            // Change the background color to blue
            selectedFab.backgroundTintList = ColorStateList.valueOf(Color.BLUE)
            // Add the selected button to the list of user selected buttons
            userSelectedButtons.add(selectedFab)

            // Get the index of the selected button
            val index = fabButtons.indexOf(selectedFab)
            // Pass this index to the FourInARow instance to set the move
            FourInARow_board.setMove(GameConstants.BLUE, index)
            // Check for a winner after the computer move
            val winner = FourInARow_board.checkForWinner()
            if (winner != GameConstants.PLAYING) {
                // Set gameEnded to true to disable buttons until the reset button is clicked
                gameEnded = true

                // Disable all buttons
                disableButtons()

                // Check the winner and display the appropriate message
                val winnerMessage = when (winner) {
                    GameConstants.BLUE_WON -> "Blue Player Wins!"
                    GameConstants.RED_WON -> "Red Player Wins!"
                    GameConstants.TIE -> "It's a Tie!"
                    else -> "HIT THE RESET BUTTON: NO WINNER DETECTED"
                }

                // Update the TextView with the winner message
                gameConditionsTextView.text = winnerMessage

                // Enable the reset button
                resetButton.isEnabled = true
            } else {
                // enable the buttons after the computer move is completed
                enableButtons()

                // text indicating it's the computer's turn
                view?.findViewById<TextView>(R.id.gameTurns)?.text = "Computer's Turn"
            }
        }
    
    }


    private lateinit var fabButtons: List<FloatingActionButton>
    private val originalTintMap = mutableMapOf<FloatingActionButton, ColorStateList?>()
    private val userSelectedButtons = mutableListOf<FloatingActionButton>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_playboard, container, false)

        // Retrieve the argument
        val playerName = arguments?.getString("PlayerName")

        // Find the TextView in the layout
        val playerNameTextView = view.findViewById<TextView>(R.id.displayName)
        gameConditionsTextView = view.findViewById<TextView>(R.id.gameConditions)

        // Set the player's name to the TextView
        playerNameTextView.text = "Hello $playerName"

        // Find all the FloatingActionButton buttons
        fabButtons = listOf(
            view.findViewById(R.id.floatingActionButton),
            view.findViewById(R.id.floatingActionButton2),
            view.findViewById(R.id.floatingActionButton3),
            view.findViewById(R.id.floatingActionButton4),
            view.findViewById(R.id.floatingActionButton5),
            view.findViewById(R.id.floatingActionButton6),
            view.findViewById(R.id.floatingActionButton7),
            view.findViewById(R.id.floatingActionButton8),
            view.findViewById(R.id.floatingActionButton9),
            view.findViewById(R.id.floatingActionButton10),
            view.findViewById(R.id.floatingActionButton11),
            view.findViewById(R.id.floatingActionButton12),
            view.findViewById(R.id.floatingActionButton13),
            view.findViewById(R.id.floatingActionButton14),
            view.findViewById(R.id.floatingActionButton15),
            view.findViewById(R.id.floatingActionButton16),
            view.findViewById(R.id.floatingActionButton17),
            view.findViewById(R.id.floatingActionButton18),
            view.findViewById(R.id.floatingActionButton19),
            view.findViewById(R.id.floatingActionButton20),
            view.findViewById(R.id.floatingActionButton21),
            view.findViewById(R.id.floatingActionButton22),
            view.findViewById(R.id.floatingActionButton23),
            view.findViewById(R.id.floatingActionButton24),
            view.findViewById(R.id.floatingActionButton25),
            view.findViewById(R.id.floatingActionButton26),
            view.findViewById(R.id.floatingActionButton27),
            view.findViewById(R.id.floatingActionButton28),
            view.findViewById(R.id.floatingActionButton29),
            view.findViewById(R.id.floatingActionButton30),
            view.findViewById(R.id.floatingActionButton31),
            view.findViewById(R.id.floatingActionButton32),
            view.findViewById(R.id.floatingActionButton33),
            view.findViewById(R.id.floatingActionButton34),
            view.findViewById(R.id.floatingActionButton35),
            view.findViewById(R.id.floatingActionButton36)
        )

        // Set OnClickListener for each FloatingActionButton and store original tint
        for (fab in fabButtons) {
            originalTintMap[fab] = fab.backgroundTintList
            // Inside the OnClickListener for each FloatingActionButton
            fab.setOnClickListener {

                // Set the text indicating it's the player's turn
                view?.findViewById<TextView>(R.id.gameTurns)?.text = "Player's Turn"
                // Disable all buttons while computer makes a move
                disableButtons()
                // Change the background color to red
                fab.backgroundTintList = ColorStateList.valueOf(Color.RED)
                // Add the clicked button to the list of user selected buttons
                userSelectedButtons.add(fab)

                // Get the index of the clicked button
                val index = fabButtons.indexOf(fab)
                // Pass this index to the FourInARow instance to set the move
                FourInARow_board.setMove(GameConstants.RED, index)

                // Check for a winner
                val winner = FourInARow_board.checkForWinner()
                if (winner != GameConstants.PLAYING) {
                    // Set gameEnded to true to disable buttons until the reset button is clicked
                    gameEnded = true

                    // Disable all buttons
                    disableButtons()

                    // Check the winner and display the appropriate message
                    val winnerMessage = when (winner) {
                        GameConstants.BLUE_WON-> "Blue Player Wins!"
                        GameConstants.RED_WON -> "Red Player Wins!"
                        GameConstants.TIE -> "It's a Tie!"
                        else -> "HIT THE RESET BUTTON: NO WINNER DETECTED"
                    }

                    // Update the TextView with the winner message
                    gameConditionsTextView.text = winnerMessage
                }
                // After the user's move, handle the computer move
                handleComputerMove()
                // Set the text indicating it's the computer's turn
                view?.findViewById<TextView>(R.id.gameTurns)?.text = "Computer's Turn"
            }
        }

        resetButton = view.findViewById(R.id.resetButton)
        // Disable the reset button initially
        resetButton.isEnabled = false

        // Set OnClickListener for the reset button
        resetButton.setOnClickListener {
            // Reset the background color of each FloatingActionButton to its original state
            for ((fab, originalTint) in originalTintMap) {
                fab.backgroundTintList = originalTint
            }
            // Clear the list of user selected buttons
            userSelectedButtons.clear()

            // Reset gameEnded flag
            gameEnded = false

            // Reset the game state
            FourInARow_board = FourInARow()

            enableButtons()

            // Disable the reset button again after resetting the game
            resetButton.isEnabled = false
        }


        return view
    }

    private fun handleComputerMove() {
        // Post the computer move with a delay
        handler.postDelayed(computerMoveRunnable, computerMoveDelayMillis)
    }

    private fun disableButtons() {
        if (!gameEnded) {
            fabButtons.forEach { it.isEnabled = false }
        }
        resetButton.isEnabled = gameEnded
    }

    private fun enableButtons() {
        if (!gameEnded) {
            fabButtons.forEach { it.isEnabled = true }
        }
        resetButton.isEnabled = false
    }
}
