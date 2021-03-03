package com.example.cattr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frame, SwipeFragment()).commit()

        swipe_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, SwipeFragment())
                .addToBackStack(null)
                .commit()
            swipe_button.isClickable = false
            chat_likes_button.isClickable = true
        }

        chat_likes_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, ChatFragment())
                .addToBackStack(null)
                .commit()
            swipe_button.isClickable = true
            chat_likes_button.isClickable = false
        }
    }
}