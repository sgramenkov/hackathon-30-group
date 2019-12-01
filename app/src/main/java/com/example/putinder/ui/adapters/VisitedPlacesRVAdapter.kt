package com.example.putinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.putinder.R

class VisitedPlacesRVAdapter(val visitedPlaces: ArrayList<String>) :
    RecyclerView.Adapter<VisitedPlacesRVAdapter.VisitedPlacesViewHolder>() {
    inner class VisitedPlacesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val visitedPlacesImageView: ImageView = view.findViewById(R.id.visited_places_image_view)
        val visitedSightsName: TextView = view.findViewById(R.id.visited_sights_name)
        val visitedSightsCategory: TextView = view.findViewById(R.id.category_sights)
        val visitedSightsDescription: TextView = view.findViewById(R.id.description_visited_sights)
        fun bind(position: Int){
            visitedSightsName.text=visitedPlaces[position]
            visitedSightsCategory.text=visitedPlaces[position]
            visitedSightsDescription.text=visitedPlaces[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitedPlacesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.visited_places_rv_item, parent, false)
        return VisitedPlacesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return visitedPlaces.size
    }

    override fun onBindViewHolder(holder: VisitedPlacesViewHolder, position: Int) {
        holder.bind(position)
    }
}