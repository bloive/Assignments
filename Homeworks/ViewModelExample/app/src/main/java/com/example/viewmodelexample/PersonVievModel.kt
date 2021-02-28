package com.example.viewmodelexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonViewModel : ViewModel() {

    val messageA = MutableLiveData<String>()
    val messageB = MutableLiveData<String>()
}