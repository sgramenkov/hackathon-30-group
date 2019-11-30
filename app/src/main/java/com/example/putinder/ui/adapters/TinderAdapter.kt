package com.example.putinder.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.putinder.MainActivity
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Photos
import com.example.putinder.ui.description.DescriptionFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_description.*
import link.fls.swipestack.SwipeStack


class TinderAdapter(private val mData: List<Photos>, val context: Context) :
    BaseAdapter() {
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
        val id = view.findViewById<TextView>(R.id.textViewCard)
        val title = view.findViewById<TextView>(R.id.sights_desc_text_view)
        Log.e("adapter",mData.toString())
        id.text=mData[position].id.toString()
        title.text=mData[position].title.toString()

        
        var descFragment=DescriptionFragment()
        descFragment.isHidden
        Glide.with(parent.context).load(mData[position].url).into(imgView)

        return view
    }

}