package com.example.pettr

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatViewModel : ViewModel() {


    val id_url = MutableLiveData<HashMap<String, String>>()
}