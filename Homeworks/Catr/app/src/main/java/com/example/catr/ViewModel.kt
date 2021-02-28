package com.example.catr
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ViewModel : ViewModel() {

    private var id_url = mutableMapOf<String, String>()

    val currentId : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val currentUrl : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun setMap (id : String, url : String) {
        id_url[id] = url
    }

    fun getMap() : LiveData<MutableMap<String, String>{
        return id_url
    }
}