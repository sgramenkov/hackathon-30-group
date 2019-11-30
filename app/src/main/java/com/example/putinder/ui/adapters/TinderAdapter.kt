package com.example.putinder.ui.adapters

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.content.Context
=======
>>>>>>> parent of 84904c6... awdad
=======
>>>>>>> parent of 84904c6... awdad
=======
>>>>>>> parent of 84904c6... awdad
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Photos

class TinderAdapter(private val mData: List<Photos>,val context: Context) :
=======
import android.widget.TextView
import com.example.putinder.R

class TinderAdapter(private val mData: ArrayList<String>) :
>>>>>>> parent of 84904c6... awdad
=======
import android.widget.TextView
import com.example.putinder.R

class TinderAdapter(private val mData: ArrayList<String>) :
>>>>>>> parent of 84904c6... awdad
=======
import android.widget.TextView
import com.example.putinder.R

class TinderAdapter(private val mData: ArrayList<String>) :
>>>>>>> parent of 84904c6... awdad
    BaseAdapter() {
    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): String {
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        val imgView = view.findViewById<ImageView>(R.id.image_view)
        Glide.with(context).load(mData[position].url).into(imgView)
=======
>>>>>>> parent of 84904c6... awdad
=======
>>>>>>> parent of 84904c6... awdad
=======
>>>>>>> parent of 84904c6... awdad
        return view
    }

}