package com.example.cattr

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatViewModel : ViewModel() {
    var urlList = MutableLiveData<ArrayList<String>>()
    var urlString = MutableLiveData<String>()
    fun addUrl (newUrl: String) {
        val newList = urlList.value
        newList?.add(newUrl)
        urlList.value = newList
        Log.d("newUrl", newUrl)
        Log.d("newList", newList.toString())
        Log.d("urlList", urlList.value.toString())

        Log.d("urlString", urlString.value.toString())
    }
}