package com.example.cattr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_status.*

class StatusActivity : AppCompatActivity() {

    var mDatabase: DatabaseReference? = null
    var mCurrentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        supportActionBar!!.title = "Update status"

        if (intent.extras != null) {
            val oldStatus = intent.extras!!.get("status").toString()
            editTextStatus.setText(oldStatus)
        }

        updateStatusButton.setOnClickListener {
            mCurrentUser = FirebaseAuth.getInstance().currentUser
            val userId = mCurrentUser!!.uid

            mDatabase = FirebaseDatabase.getInstance().reference
                    .child("Users")
                    .child(userId)

            val status = editTextStatus.text.toString().trim()
            mDatabase!!.child("status")
                    .setValue(status).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                    this,
                                    "Status updated successfully",
                                    Toast.LENGTH_SHORT)
                                    .show()
                            finish()
                        } else {
                            Toast.makeText(
                                    this,
                                    "Something went wrong",
                                    Toast.LENGTH_SHORT)
                                    .show()
                            finish()
                        }
                    }
        }
    }
}