package com.example.catr

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class ChatFragment : Fragment() {
    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        putImage()
    }

    @SuppressLint("ResourceType")
    private fun putImage() {
        Log.d("viewmodel", "current url ${viewModel.currentUrl.value}")
        Log.d("viewmodel", "current id ${viewModel.currentId.value}")
//        var newView : ImageView = ImageView(activity)
//        newView.id = 7
//        view!!.findViewById<ScrollView>(R.id.scrlView).addView(newView)
//
//        Picasso.get()
//            .load(viewModel.currentUrl.value)
//            .into(view!!.findViewById<ImageView>(7))
    }
}