package com.example.putinder.ui.description

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.putinder.MainActivity
import com.example.putinder.R

class DescriptionFragment : Fragment() {
    lateinit var descpref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_description, container, false)
        descpref = (context as MainActivity).getSharedPreferences("DESCRIPTION", MODE_PRIVATE)
        val desc=descpref.getString("DESC", "")
        Log.e("DESC",desc)
        val descTV = view.findViewById<TextView>(R.id.desc_tv)
        descTV.text=desc
        val reviewTV = view.findViewById<TextView>(R.id.reviews_tv)
        return view
    }
}