package com.example.catr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.koushikdutta.ion.Ion

class Main : AppCompatActivity() {
    private lateinit var data: Map<String, String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val swipeButton = findViewById<Button>(R.id.swipe_button)
        val chatLikesButton = findViewById<Button>(R.id.chat_likes_button)
        val settingsButton = findViewById<Button>(R.id.settings_button)

        supportFragmentManager.beginTransaction().replace(R.id.frame, SwipeFragment()).commit()

        swipeButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.frame, SwipeFragment()).commit()
            swipeButton.isClickable = false
            chatLikesButton.isClickable = true
            settingsButton.isClickable = true
        }

        chatLikesButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.frame, ChatFragment()).commit()
            chatLikesButton.isClickable = false
            swipeButton.isClickable = true
            settingsButton.isClickable = true
        }

        settingsButton.setOnClickListener {
            //supportFragmentManager.beginTransaction().replace(R.id.frame, SettingsFragment()).commit()
            settingsButton.isClickable = false
            swipeButton.isClickable = true
            chatLikesButton.isClickable = true
        }
    }
    public fun getData(data: Map<String, String>) {
        this.data = data
    }
}