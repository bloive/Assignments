package com.example.pettr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (intent.extras != null) {
            val username = intent.extras!!.get("name")
            Toast.makeText(this, username.toString(), Toast.LENGTH_SHORT).show()
        }

        supportFragmentManager.beginTransaction().add(R.id.viewPager, SwipeFragment()).commit()

        swipe_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.viewPager, SwipeFragment())
                .addToBackStack(null)
                .commit()
            swipe_button.isClickable = false
            chat_likes_button.isClickable = true
        }

        chat_likes_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.viewPager, ChatFragment())
                .addToBackStack(null)
                .commit()
            swipe_button.isClickable = true
            chat_likes_button.isClickable = false
        }
    }
}