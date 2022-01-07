package com.example.projectfinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }
    fun goCamera(sender: View)
    {
        val intent = Intent(context, Camera::class.java)

        startActivity(intent)

    }

    fun goTicTacToe(sender: View)
    {
        val intent = Intent(context, TicTacToe::class.java)

        startActivity(intent)

    }


}