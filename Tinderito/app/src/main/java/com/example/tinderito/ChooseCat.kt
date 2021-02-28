package com.example.tinderito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONObject

class ChooseCat : AppCompatActivity() {
    private var catList : ArrayList<String> = arrayListOf<String>()
    private val url = "http://api.thecatapi.com/api/images/get?format=json&size=med&results_per_page=1"
    private var currentUrl: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_cat)

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.putStringArrayListExtra("catList", catList)
            startActivity(intent)
        }

        Ion.with(this)
                .load(url)
                .asString()
                .setCallback { _, result ->
                    Log.d("elene", "ION JSON DATA:\n$result")
                    processCatData(result)
                }
    }

    /* [
          {
            "id": "133",
            "url": "https://26.media.tumblr.com/tumblr_llcncb8uAG1qjahcpo1_500.jpg",
            "source_url": "https://thecatapi.com/?image_id=133"
          },
          {
            "id": "1ur",
            "url": "https://29.media.tumblr.com/tumblr_m1ebmkbKPw1qzdnnwo1_500.png",
            "source_url": "https://thecatapi.com/?image_id=1ur"
          }
        ]
     */
    private fun processCatData(result: String) {
        val json = JSONObject("{\"images\":$result}")
        val imagesArray = json.getJSONArray("images")
        val firstImage = imagesArray.getJSONObject(0)
        val imageUrl = firstImage.getString("url")

        currentUrl = imageUrl
        Picasso.get()
            .load(imageUrl)
            .into(findViewById<ImageView>(R.id.catImage))
    }

    fun clickLike(view: View) {
        if (currentUrl.isNotEmpty()) {
            catList.add(currentUrl)
        }
        Log.d("elene", "click")
        Ion.with(this).load(url)
            .asString()
            .setCallback { _, result ->
                //code
                Log.d("elene", "the JSON data is:\n$result")
                processCatData(result)
            }
    }
}