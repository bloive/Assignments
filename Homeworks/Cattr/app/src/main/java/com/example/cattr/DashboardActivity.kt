package com.example.cattr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (intent.extras != null) {
            val username = intent.extras!!.get("name")
            Toast.makeText(this, username.toString(), Toast.LENGTH_SHORT).show()
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == R.id.logout) {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, StartActivity::class.java))
        }
        if (item.itemId == R.id.settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        return true
    }
}