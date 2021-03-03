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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance();

        val mainIntent = Intent(this, MainActivity::class.java)

        titleSignup.setOnClickListener {
            finish()
        }

        buttonSignupSignup.setOnClickListener {
            registerUser()
            startActivity(mainIntent)
        }
    }

    private fun registerUser() {
        val name = editTextNameSignUp.text.toString().trim()
        val email = editTextEmailSignUp.text.toString().trim()
        val password = editTextTextPasswordSignup.text.toString().trim()

        if (name.isEmpty()) {
            editTextNameSignUp.error = "Name is required"
            editTextNameSignUp.requestFocus()
        }
        if (email.isEmpty()) {
            editTextEmailSignUp.error = "Email is required"
            editTextEmailSignUp.requestFocus()
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailSignUp.error = "Provide valid email"
            editTextEmailSignUp.requestFocus()
        }
        if (password.isEmpty()) {
            editTextTextPasswordSignup.error = "Password is required"
            editTextTextPasswordSignup.requestFocus()
        }
        if (password.length < 6) {
            editTextTextPasswordSignup.error = "Password must contain at least 6 characters"
            editTextTextPasswordSignup.requestFocus()
        }
        progressBarRegister.visibility = View.VISIBLE

        //Firebase Registration

        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(OnCompleteListener<AuthResult> {task ->
                //If registration is successful
                if (task.isSuccessful) {
                    val user = User(name, email)
                    FirebaseAuth.getInstance().currentUser?.let {
                        FirebaseDatabase.getInstance().getReference("Users")
                            .child(it.uid).setValue(user)
                            .addOnCompleteListener(OnCompleteListener<Void> {task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "You have been registered successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    progressBarRegister.visibility = View.GONE
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