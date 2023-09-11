package com.example.week2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import java.lang.Exception

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var score = 0;

        val txtNumber1 = view.findViewById<TextView>(R.id.txtNumber1)
        val txtNumber2 = view.findViewById<TextView>(R.id.txtNumber2)
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            var playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
            txtTurn.text = "$playerName's Turn"
        }
        var rands = (0..101).random()
        var rands2 = (0..101).random()
        txtNumber1.setText(rands.toString())
        txtNumber2.setText(rands2.toString())
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            try {
                val answer = view.findViewById<TextView>(R.id.txtAnswer).text.toString()
                view.findViewById<TextView>(R.id.txtAnswer).setText("")
                val a = answer.toInt()
                if (a === (rands + rands2)){
                    score++
                    rands = (0..101).random()
                    rands2 = (0..101).random()
                    txtNumber1.setText(rands.toString())
                    txtNumber2.setText(rands2.toString())

                }
                else{
                    throw Exception("WRONG ANSWER");
                }
            } catch (e: Exception) {
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }


    }
}