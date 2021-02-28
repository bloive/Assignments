package com.example.tinderito

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private var likedCatsUrl = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.findNewCat).setOnClickListener {
            val intent = Intent(this, ChooseCat::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(
            "elene",
            "ONNEWINTENT ARRAYLIST" + intent?.getStringArrayListExtra("catList").toString()
        )
        if (intent != null) {
            likedCatsUrl.addAll(intent.getStringArrayListExtra("catList")!!)
        }
        val adapter = CatAdapter(this, likedCatsUrl)
        findViewById<GridView>(R.id.gridView).adapter = adapter
    }

    class CatAdapter(context: Context, var catList: ArrayList<String>) : BaseAdapter() {
        var context: Context? = context

        override fun getCount(): Int {
            Log.d(
                    "elene",
                    catList.size.toString()
            )
            return catList.size
        }

        override fun getItem(position: Int): Any {
            return catList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val cat = this.catList[position]

            val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val catPreview = inflator.inflate(R.layout.cat_preview, null)

            Picasso.get()
                    .load(cat)
                    .into(catPreview.findViewById<ImageView>(R.id.image))

            return catPreview
        }
    }
}