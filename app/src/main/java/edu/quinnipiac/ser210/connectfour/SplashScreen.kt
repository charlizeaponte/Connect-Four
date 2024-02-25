package edu.quinnipiac.ser210.connectfour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        val startButton = view.findViewById<Button>(R.id.button)
        val playerNameEditText = view.findViewById<EditText>(R.id.name)

        startButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString()
            val action = SplashScreenDirections.actionSplashScreenToPlayboard(playerName)
            view.findNavController().navigate(action)
        }
        return view
    }


}