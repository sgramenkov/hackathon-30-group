package com.example.putinder.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.putinder.R
import com.example.putinder.model.Sights
import org.w3c.dom.Text

class TinderAdapter(val mData: ArrayList<Sights>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): String {
        return mData[position].toString()
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
        val sightsImageView:ImageView=view.findViewById(R.id.sights_image_view)
        val sightsName:TextView=view.findViewById(R.id.sights_name_text_view)
        val sightsDesc:TextView=view.findViewById(R.id.sights_desc_text_view)
        Log.e("class",mData.toString())
        Glide.with(parent.context).load(mData[position].url).into(sightsImageView)
            sightsName.text=Sights().id.toString()
            sightsDesc.text=Sights().albumId.toString()
        return view
    }

}