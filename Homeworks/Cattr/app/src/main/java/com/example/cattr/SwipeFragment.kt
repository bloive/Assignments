package com.example.cattr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_swipe.*
import org.json.JSONObject

class SwipeFragment : Fragment() {
    private lateinit var catViewModel: CatViewModel
    private var urlSwipe = mutableListOf<String>()
    private var currentID = ""
    private var currentUrl = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        nextCat()
        Log.d("life", "onCreateView")
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        like_button.setOnClickListener {
            Log.d("cat", "LIKE")

            like()
        }

        imageView.setOnClickListener {
            Log.d("cat", "NEXT")
            nextCat()
        }
    }
    override fun onResume() {
        super.onResume()
        catViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
                .get(CatViewModel::class.java)
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
    private fun processCatData(result: String) {
        val json = JSONObject("{\"image\":$result}")
            .getJSONArray("image")
            .getJSONObject(0)
        currentID = json.getString("id")
        currentUrl = json.getString("url")
        Picasso.get()
            .load(currentUrl)
                .into(imageView)
    }
    private fun like() {
        urlSwipe.add(currentUrl)
        catViewModel.addUrl(currentUrl)
        Log.d("urlSwipe", "$urlSwipe")
        Log.d("currentUrl", "$currentUrl")

        catViewModel.urlString.value = currentUrl
    }
}