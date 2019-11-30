package com.example.putinder.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.putinder.R

class DescriptionFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=LayoutInflater.from(context).inflate(R.layout.fragment_description,container,false)
        val descTV=view.findViewById<TextView>(R.id.desc_tv)
        val reviewTV=view.findViewById<TextView>(R.id.reviews_tv)
        return view
    }
}