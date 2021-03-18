package com.example.cattr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_likes.*

class LikesFragment : Fragment() {

    private lateinit var catViewModel: CatViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likes, container, false)
    }

    override fun onResume() {
        super.onResume()

        catViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
            .get(CatViewModel::class.java)
//        Log.d("----", "${catViewModel.url_list.value}")
        val likedUrl = TextView(activity)
        likedUrl.text = catViewModel.urlList.value.toString()
        LinearLayoutLikes.addView(likedUrl)

    }
}