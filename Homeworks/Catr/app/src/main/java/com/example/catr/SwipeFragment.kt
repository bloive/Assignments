package com.example.catr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONObject

class SwipeFragment : Fragment() {
    lateinit var viewModel: ViewModel

    private lateinit var currentUrl: String
    private lateinit var currentID: String
    private var id_url = mutableMapOf<String, String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initFragments()

        nextCat()
        Log.d("life", "onCreateView")
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("life", "onActivityCreated")
        view!!.findViewById<Button>(R.id.like_button).setOnClickListener {
            Log.d("cat", "LIKE")
            like()
        }
        view!!.findViewById<ImageView>(R.id.imageView).setOnClickListener {
            Log.d("cat", "NEXT")
            nextCat()
        }
    }

    private fun nextCat() {
        Ion.with(this)
            .load("https://api.thecatapi.com/v1/images/search")
            .asString()
            .setCallback { _, result ->
                Log.d("cat", "the JSON is $result")
                processCatData(result)
            }
    }
    /*
    [
        {
            "breeds":[],
            "id":"5as",
            "url":"https://cdn2.thecatapi.com/images/5as.jpg",
            "width":500,
            "height":410
        }
     ]
     */

    private fun processCatData(result: String) {
        val json = JSONObject("{\"image\":$result}")
            .getJSONArray("image")
            .getJSONObject(0)
        currentID = json.getString("id")
        currentUrl = json.getString("url")
        viewModel.currentId.value = currentID
        Log.d("current", "current id is $currentID")
        Log.d("current", "current viewmod ${viewModel.currentId.value}")
        viewModel.currentUrl.value = currentUrl
        Picasso.get()
            .load(currentUrl)
            .into(view!!.findViewById<ImageView>(R.id.imageView))

    }
    
    private fun like() {
        id_url[currentID] = currentUrl
        Log.d("cat", "$id_url")
    }

}


