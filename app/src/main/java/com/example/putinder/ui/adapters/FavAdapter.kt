package com.example.putinder.ui.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.putinder.MainActivity
import com.example.putinder.MoreInfoActivity
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Sights

class FavAdapter(val list: List<Sights>, val context: Context):
    RecyclerView.Adapter<FavAdapter.PhotosHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {
        return PhotosHolder(LayoutInflater.from(context).inflate( R.layout.photos_item,parent,false))
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
        val photo=list[position]
        holder.bind(photo,position)
    }

    inner class PhotosHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        private val imageView=itemView.findViewById<ImageView>(R.id.photos_image_view)
        private val idTextView=itemView.findViewById<TextView>(R.id.id_text)
        private val typeTextView=itemView.findViewById<TextView>(R.id.type_text)
        private val titleTextView=itemView.findViewById<TextView>(R.id.title_text)
        fun bind(sights: Sights, position: Int){
            idTextView.text=sights.placeName
            typeTextView.text=sights.typePlace
            titleTextView.text=sights.description
            Glide.with(context).load(sights.placeImg).into(imageView)
            itemView.setOnClickListener(){
                val intent=Intent(itemView.context,MoreInfoActivity::class.java)
                itemView.context.startActivity(intent)
            }
            Glide.with(context).load(sights.placeImg).centerCrop().into(imageView)
        }
    }
}