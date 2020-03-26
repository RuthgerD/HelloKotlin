package com.example.helloworld.ui.nothing2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloworld.R

class Nothing2Fragment : Fragment() {

    private lateinit var nothing2ViewModel: Nothing2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nothing2ViewModel =
            ViewModelProviders.of(this).get(Nothing2ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_nothing2, container, false)
        val textView: TextView = root.findViewById(R.id.text_nothing2)
        nothing2ViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
