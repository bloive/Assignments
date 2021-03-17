package com.example.pettr

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        titleSignup.setOnClickListener {
            finish()
        }

        buttonSignupSignup.setOnClickListener {
            val name = editTextNameSignUp.text.toString().trim()
            val email = editTextEmailSignUp.text.toString().trim()
            val password = editTextPasswordSignup.text.toString().trim()
            if (validateInput(name, email, password)) {
                registerUser(name, email, password)
            }
        }
    }

    private fun validateInput(name: String, email: String, password: String): Boolean {
        var n: Boolean
        if (name.isEmpty()) {
            n = false
            editTextNameSignUp.error = "Name is required"
            editTextNameSignUp.requestFocus()
        } else {
            n = true
        }
        if (email.isEmpty()) {
            n = false
            editTextEmailSignUp.error = "Email is required"
            editTextEmailSignUp.requestFocus()
        } else {
            n = true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            n = false
            editTextEmailSignUp.error = "Provide valid email"
            editTextEmailSignUp.requestFocus()
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

    private  fun registerUser(name: String, email: String, password: String) {
        progressBarRegister.visibility = View.VISIBLE
        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    val currentUser = mAuth!!.currentUser
                    val userId = currentUser!!.uid

                    mDatabase = FirebaseDatabase.getInstance().reference
                        .child("Users").child(userId)

                    val userObject = HashMap<String, String>()
                    userObject["name"] = name
                    userObject["status"] = "Hi there"
                    userObject["image"] = "default"

                    mDatabase!!.setValue(userObject).addOnCompleteListener {
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "User created",
                                Toast.LENGTH_SHORT
                            ).show()
                            progressBarRegister.visibility = View.GONE
                            val mainIntent = Intent(this, DashboardActivity::class.java)
                                .putExtra("name", name)
                            startActivity(mainIntent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "User not created",
                                Toast.LENGTH_SHORT
                            ).show()
                            progressBarRegister.visibility = View.GONE
                        }
                    }
                }
            })
    }
}