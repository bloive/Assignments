package com.example.cattr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val signupIntent = Intent(this, SignupActivity::class.java)
        val loginIntent = Intent(this, LoginActivity::class.java)

        buttonSignupStart.setOnClickListener {
            startActivity(signupIntent)
        }

        buttonLoginStart.setOnClickListener {
            startActivity(loginIntent)
        }
    }
}