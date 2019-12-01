package com.example.putinder.ui.adapters


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.putinder.MainActivity
import com.example.putinder.MoreInfoActivity
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Sights

class FavAdapter(val list: List<Sights>, val context: Context):
    RecyclerView.Adapter<FavAdapter.PhotosHolder>() {
    lateinit var descpref: SharedPreferences
    lateinit var descprefEditor: SharedPreferences.Editor
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
        val btnPath = itemview.findViewById<ImageButton>(R.id.path_image)
        fun bind(sights: Sights, position: Int){
            idTextView.text=sights.placeName
            typeTextView.text=sights.typePlace

            btnPath.setOnClickListener{
                val gmmIntentUri: Uri = Uri.parse("google.navigation:q=${sights.placeGeoX}+${sights.placeGeoY}&mode=w")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                ContextCompat.startActivity(context, mapIntent, null)
            }
            Glide.with(context).load(sights.placeImg).into(imageView)
            descpref = (context as MainActivity).getSharedPreferences("DESCRIPTION",
                Context.MODE_PRIVATE
            )
            itemView.setOnClickListener{
                val intent=Intent(itemView.context,MoreInfoActivity::class.java)
                descprefEditor = descpref.edit()
                descprefEditor.putString("DESC", sights.description)
                descprefEditor.putString("photo",sights.placeImg)
                descprefEditor.putString("name",sights.placeName)
                descprefEditor.putString("category",sights.typePlace)
                descprefEditor.apply()
                itemView.context.startActivity(intent)
            }

            Glide.with(context).load(sights.placeImg).centerCrop().into(imageView)
        }
    }
}