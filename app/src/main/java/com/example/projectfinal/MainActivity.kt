package com.example.projectfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun goTicTacToe(sender: View)
    {
        val intent = Intent(this, TicTacToe::class.java)

        startActivity(intent)

    }
    fun goCamera(sender: View)
    {
        val intent = Intent(this, Camera::class.java)

        startActivity(intent)

    }
}