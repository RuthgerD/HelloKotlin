package com.example.helloworld.ui.nothing1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloworld.R

class Nothing1Fragment : Fragment() {

    private lateinit var nothing1ViewModel: Nothing1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nothing1ViewModel =
            ViewModelProviders.of(this).get(Nothing1ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_nothing1, container, false)
        val textView: TextView = root.findViewById(R.id.text_nothing1)
        nothing1ViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
