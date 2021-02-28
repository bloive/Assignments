package com.example.resources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListAdapter

class GridViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)
    }

    val grid = findViewById<GridView>(R.id.grid)
    val adapter = ListAdapter

}