package com.example.putinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.putinder.R

class NewsRVAdapter(val newsList: ArrayList<String>) :
    RecyclerView.Adapter<NewsRVAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sourceImageView: ImageView = view.findViewById(R.id.news_source_image_view)
        val newsLinkTV: TextView = view.findViewById(R.id.news_link_text_view)
        val postImageView: ImageView = view.findViewById(R.id.post_image_view)
        val articleNameTextView: TextView = view.findViewById(R.id.article_name_text_view)
        val articleDescTextView: TextView = view.findViewById(R.id.article_description_text_view)
        val articleDateTextView: TextView = view.findViewById(R.id.article_date_text_view)
        fun bind(position: Int) {
            newsLinkTV.text = newsList[position]
            articleNameTextView.text = newsList[position]
            articleDateTextView.text = newsList[position]
            articleDescTextView.text = newsList[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_rv_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(position)
    }
}