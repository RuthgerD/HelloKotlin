package com.example.helloworld.ui.nothing2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hellocore.commonFunc

class Nothing2ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = commonFunc()
    }
    val text: LiveData<String> = _text
}