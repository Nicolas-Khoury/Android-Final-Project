package com.example.projectfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.projectfinal.databinding.ActivityMainBinding
import com.example.projectfinal.databinding.ActivityTicTacToeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TicTacToe : AppCompatActivity() {

    enum class Turn{
        CIRCLE,CROSS
    }
    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var XScore = 0
    private var OScore = 0
    private var boardList = mutableListOf<Button>()
    private lateinit var binding : ActivityTicTacToeBinding


    private lateinit var X: DatabaseReference
    private lateinit var O: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        val database = Firebase.database
        X = database.getReference("X")
        O = database.getReference("O")

        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)

        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)

        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        addToBoard(view)

        if(checkForWinner(CIRCLE)){
            OScore++
            O.setValue(OScore)
            result("Player O WINS!")}
        if(checkForWinner(CROSS)){
            XScore++
            X.setValue(XScore)
            result("Player X WINS!")}
        if(fullBoard()){
            result("Draw")
        }
    }

    private fun checkForWinner(s: String): Boolean {
        //Horizontal Win
        if(match(binding.a1,s)&& match(binding.a2,s)&& match(binding.a3,s))
            return true
        if(match(binding.b1,s)&& match(binding.b2,s)&& match(binding.b3,s))
            return true
        if(match(binding.c1,s)&& match(binding.c2,s)&& match(binding.c3,s))
            return true

        //Vertical Win
        if(match(binding.a1,s)&& match(binding.b1,s)&& match(binding.c1,s))
            return true
        if(match(binding.a2,s)&& match(binding.b2,s)&& match(binding.c2,s))
            return true
        if(match(binding.a3,s)&& match(binding.b3,s)&& match(binding.c3,s))
            return true

        //Diagonal Win
        if(match(binding.a1,s)&& match(binding.b2,s)&& match(binding.c3,s))
            return true
        if(match(binding.a3,s)&& match(binding.b2,s)&& match(binding.c1,s))
            return true

        return false
    }

    private fun match(button:Button, symbol :String):Boolean =button.text== symbol


    private fun result(title: String) {
        val message ="\nPlayer O won $OScore times \n\nPlayer X won $XScore times"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset Board")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for(button in boardList)
          {
              button.text = ""
          }

        if(firstTurn == Turn.CIRCLE)
            firstTurn= Turn.CROSS
        else if(firstTurn == Turn.CROSS)
            firstTurn= Turn.CIRCLE

        currentTurn = firstTurn
        setTurnLable()
    }

    private fun fullBoard(): Boolean {
     for(button in boardList){
         if(button.text == "")
             return false
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if(button.text != "")
            return

        if(currentTurn == Turn.CIRCLE){
            button.text = CIRCLE
            currentTurn= Turn.CROSS
        }
        else if(currentTurn == Turn.CROSS){
            button.text = CROSS
            currentTurn= Turn.CIRCLE
        }
        setTurnLable()

    }


    private fun setTurnLable() {
        var turnText = ""
        if(currentTurn == Turn.CROSS)
            turnText= "Turn $CROSS"
        else if(currentTurn == Turn.CIRCLE)
            turnText= "Turn $CIRCLE"

        binding.turnTextView.text= turnText
    }


    companion object{
        const val CIRCLE = "O"
        const val CROSS  = "X"

    }
}