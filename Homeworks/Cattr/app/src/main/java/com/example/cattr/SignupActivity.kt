package com.example.cattr

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private lateinit var mainIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance();

        mainIntent = Intent(this, MainActivity::class.java)

        titleSignup.setOnClickListener {
            finish()
        }

        buttonSignupSignup.setOnClickListener {
            registerUser()
        }
    }

    private  fun registerUser() {
        var n = 0
        val name = editTextNameSignUp.text.toString().trim()
        val email = editTextEmailSignUp.text.toString().trim()
        val password = editTextTextPasswordSignup.text.toString().trim()

        if (name.isEmpty()) {
            n = 0
            editTextNameSignUp.error = "Name is required"
            editTextNameSignUp.requestFocus()
        } else {
            n = 1
        }
        if (email.isEmpty()) {
            n = 0
            editTextEmailSignUp.error = "Email is required"
            editTextEmailSignUp.requestFocus()
        } else {
            n = 1
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            n = 0
            editTextEmailSignUp.error = "Provide valid email"
            editTextEmailSignUp.requestFocus()
        } else {
            n = 1
        }
        if (password.isEmpty()) {
            n = 0
            editTextTextPasswordSignup.error = "Password is required"
            editTextTextPasswordSignup.requestFocus()
        } else {
            n = 1
        }
        if (password.length < 6) {
            n = 0
            editTextTextPasswordSignup.error = "Password must contain at least 6 characters"
            editTextTextPasswordSignup.requestFocus()
        } else {
            n = 1
        }

        //Firebase Registration
        if (n == 1) {
            progressBarRegister.visibility = View.VISIBLE
            mAuth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                        //If registration is successful
                        if (task.isSuccessful) {
                            val user = User(name, email)
                            FirebaseAuth.getInstance().currentUser?.let {
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(it.uid).setValue(user)
                                        .addOnCompleteListener(OnCompleteListener<Void> { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(
                                                        this,
                                                        "You have been registered successfully!",
                                                        Toast.LENGTH_SHORT
                                                ).show()
                                                progressBarRegister.visibility = View.GONE
                                                startActivity(mainIntent)

                                            } else {
                                                Toast.makeText(
                                                        this,
                                                        "Registration failed, try again.",
                                                        Toast.LENGTH_SHORT
                                                ).show()
                                                progressBarRegister.visibility = View.GONE
                                            }
                                        })
                            }
                        } else {
                            Toast.makeText(
                                    this,
                                    "Registration failed, try again.",
                                    Toast.LENGTH_SHORT
                            ).show()
                            progressBarRegister.visibility = View.GONE
                        }
                    })
        }
    }
}