package com.example.putinder.ui.adapters

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.putinder.MainActivity
import com.example.putinder.MoreInfoActivity
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Sights
import java.math.BigDecimal
import java.math.RoundingMode


class TinderAdapter(private val mData: List<Sights>, val context: Context) :
    BaseAdapter() {
    lateinit var descpref: SharedPreferences
    lateinit var descprefEditor: SharedPreferences.Editor
    var myLat: Double? = null
    var myLon: Double? = null

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): String {
        return mData[position].placeImg!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun coordToMeters(lat1: Double, lat2: Double, lon1: Double, lon2: Double): Int {
        var R = 6371e3
        var x1 = Math.toRadians(lat1)
        var x2 = Math.toRadians(lat2)
        var dx = Math.toRadians(lat2 - lat1)
        var dy = Math.toRadians(lon2 - lon1)
        var a = Math.sin(dx / 2) * Math.sin(dx / 2) +
                Math.cos(x1) * Math.cos(x2) *
                Math.sin(dy / 2) * Math.sin(dy / 2)
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        var d = R * c
        return d.toInt()

    }

    fun formDist(dist: Int): String {

        var dDist: Double = (dist.toDouble() / 1000)
        if (dist > 1000) {
            var s = (BigDecimal(dDist).setScale(1, RoundingMode.HALF_EVEN)).toString() + "км"
            Log.e("dist", s)
            return s
        } else {

            return (Math.round(dist / 10.0) * 10).toString() + "м"
        }


    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        descpref = (context as MainActivity).getSharedPreferences("DESCRIPTION", MODE_PRIVATE)
        descprefEditor = descpref.edit()

        val view =
            LayoutInflater.from(parent!!.context).inflate(R.layout.tinder_card, parent, false)
        val imgView = view.findViewById<ImageView>(R.id.image_view)
        val placeName = view.findViewById<TextView>(R.id.textViewCard)
        val title = view.findViewById<TextView>(R.id.sights_desc_text_view)
        val textDist = view.findViewById<TextView>(R.id.text_distance)
        var lat = mData[position].placeGeoX!!.toDouble()
        var lng = mData[position].placeGeoY!!.toDouble()
        myLat=descpref.getFloat("myLat",0f).toDouble()
        myLon=descpref.getFloat("myLon",0f).toDouble()

        var dist = coordToMeters(myLat!!, lat, myLon!!, lng)
        textDist.text = formDist(dist)


        var routeButton = view.findViewById<ImageButton>(R.id.routeButton)
        routeButton.setOnClickListener() {
            val gmmIntentUri: Uri = Uri.parse("google.navigation:q=$lat+$lng&mode=w")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(context, mapIntent, null)
        }

        Log.e("adapter", mData.toString())
        if (position < mData.size) {
            placeName.text = mData[position].placeName
            title.text = mData[position].description.toString()
            Glide.with(parent.context).load(mData[position].placeImg).centerCrop().into(imgView)
        }
        val moreInfoBTN: Button = view.findViewById(R.id.more_info_btn)

        moreInfoBTN.setOnClickListener {
            descprefEditor = descpref.edit()
            descprefEditor.putString("DESC", mData[position].description)
            descprefEditor.putString("photo",mData[position].placeImg)
            descprefEditor.putString("name",mData[position].placeName)
            descprefEditor.putString("category",mData[position].typePlace)
            descprefEditor.apply()

            val intent=Intent(context,MoreInfoActivity::class.java)
            startActivity(context,intent,null)
        }



        return view
    }


}