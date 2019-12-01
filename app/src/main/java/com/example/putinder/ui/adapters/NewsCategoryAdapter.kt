package com.example.putinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.putinder.R

class NewsCategoryAdapter(val categoriesList: ArrayList<String>) :
    RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryViewHolder>() {
    inner class NewsCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoriesTV:TextView=view.findViewById(R.id.categories_text_view)
        fun bind(position: Int){
            categoriesTV.text=categoriesList[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_category_rv_item, parent, false)
        return NewsCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: NewsCategoryViewHolder, position: Int) {
        holder.bind(position)
    }
}