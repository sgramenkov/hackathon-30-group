package com.example.putinder.ui.adapters


import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Photos

class TinderAdapter(private val mData: List<Photos>,val context: Context) :
BaseAdapter()

{
    override fun getCount(): Int {
        return 20
    }

    override fun getItem(position: Int): String {
        return mData[position].url
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.tinder_card,parent,false)

        val imgView = view.findViewById<ImageView>(R.id.image_view)
        val tv=view.findViewById<TextView>(R.id.sights_desc_text_view)
        tv.text=mData[position].url.toString()
       // Glide.with(context).load(mData[position].url).into(imgView)

        return view
    }

}