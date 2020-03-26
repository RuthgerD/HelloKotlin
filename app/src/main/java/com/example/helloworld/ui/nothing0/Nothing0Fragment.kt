package com.example.helloworld.ui.nothing0

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.R

class Nothing0Fragment : Fragment() {

    private lateinit var model: Nothing0ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(this).get(Nothing0ViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_nothing0, container, false)

        val textView: TextView = root.findViewById(R.id.text_home)
        model.counter.observe(viewLifecycleOwner, Observer {
            textView.text = it.toString()
        })

        return root
    }

    fun increaseCounter(view: View) {
        model.counter.apply { value = value?.plus(1) }
    }
}
