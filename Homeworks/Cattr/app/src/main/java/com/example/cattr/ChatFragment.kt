package com.example.cattr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    private lateinit var catViewModel: CatViewModel
    private var id_url = HashMap<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        catViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
                .get(CatViewModel::class.java)

        textView.text = catViewModel.id_url.toString()
        Log.d("url", "HASHMAP is ${id_url.toString()}")
    }
}