package com.example.cattr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            firebaseAuth ->
            currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        }

        val signUpIntent = Intent(this, SignUpActivity::class.java)
        val loginIntent = Intent(this, LogInActivity::class.java)

        buttonSignupStart.setOnClickListener {
            startActivity(signUpIntent)
        }

        buttonLoginStart.setOnClickListener {
            startActivity(loginIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }
}