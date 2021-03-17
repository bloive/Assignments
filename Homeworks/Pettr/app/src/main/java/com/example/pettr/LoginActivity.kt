package com.example.pettr

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*


class LogInActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        titleLogin.setOnClickListener {
            finish()
        }

        buttonLoginLogin.setOnClickListener {
            val email = editTextEmailLogin.text.toString().trim()
            val password = editTextPasswordLogin.text.toString().trim()
            if (validateInput(email, password)) {
                logInUser(email, password)
            }
        }
    }
    private fun validateInput(email: String, password: String): Boolean {
        var n: Boolean
        if (email.isEmpty()) {
            n = false
            editTextEmailLogin.error = "Email is required"
            editTextEmailLogin.requestFocus()
        } else {
            n = true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            n = false
            editTextEmailLogin.error = "Provide valid email"
            editTextEmailLogin.requestFocus()
        } else {
            n = true
        }
        if (password.isEmpty()) {
            n = false
            editTextPasswordSignup.error = "Password is required"
            editTextPasswordSignup.requestFocus()
        } else {
            n = true
        }
        if (password.length < 6) {
            n = false
            editTextPasswordSignup.error = "Password must contain at least 6 characters"
            editTextPasswordSignup.requestFocus()
        } else {
            n = true
        }
        return n
    }

    private  fun logInUser(email: String, password: String) {
        progressBarLogin.visibility = View.VISIBLE
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                    task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Signed in successfully",
                        Toast.LENGTH_SHORT)
                        .show()
                    val username = email.split("@")[0]
                    val mainIntent = Intent(this, DashboardActivity::class.java)
                        .putExtra("name", username)
                    startActivity(mainIntent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Login failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }
}