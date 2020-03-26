package com.example.helloworld.ui.nothing0

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Nothing0ViewModel : ViewModel() {

    val counter = MutableLiveData<Int>().apply {
        value = 0
    }

    override fun onCleared() {
        println("cleared")
        super.onCleared()
    }
}