package com.example.putinder.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.putinder.R
import com.example.putinder.Retrofit.Models.Photos
import com.example.putinder.Retrofit.RetrofitService
import com.example.putinder.retrofit.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import link.fls.swipestack.SwipeStack
import retrofit2.HttpException

class TinderAdapter(private val mData: ArrayList<Photos>,val context: Context) :
    BaseAdapter() {
    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): Photos {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?

    ): View {
        /*var convertView: View? = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context).inflate(R.layout.tinder_card, parent, false)
        }
        val textViewCard =
            convertView?.findViewById<View>(R.id.textViewCard) as TextView
        textViewCard.text = mData[position]
        return convertView*/


        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.tinder_card,parent,false)
        //val imgView = view.findViewById<ImageView>(R.id.)
        //Glide.with(context).load(mData[position].url).into()
        return view
    }


}