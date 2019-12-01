package com.example.putinder.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.putinder.R
import com.example.putinder.ui.adapters.VisitedPlacesRVAdapter

class ProfileFragment:Fragment() {
    lateinit var visitedPlaces:ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        visitedPlaces= arrayListOf()

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val visitedPlacesRV: RecyclerView =root.findViewById(R.id.visited_places_rv)
        val lm =LinearLayoutManager(context)
        visitedPlacesRV.layoutManager=lm
        visitedPlacesRV.adapter=VisitedPlacesRVAdapter(visitedPlaces)

        for (i in 1..100){
            visitedPlaces.add(i.toString())
        }
        return root

    }
}