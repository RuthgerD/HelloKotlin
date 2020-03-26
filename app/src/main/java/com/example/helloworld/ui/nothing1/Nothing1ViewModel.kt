package com.example.helloworld.ui.nothing1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hellocore.platformFunc

class Nothing1ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = platformFunc()
    }
    val text: LiveData<String> = _text
}