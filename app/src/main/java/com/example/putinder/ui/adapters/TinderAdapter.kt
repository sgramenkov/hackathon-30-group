package com.example.putinder.ui.adapters

import android.content.Context
<<<<<<< Updated upstream
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
=======
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
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
    lateinit var descpref: SharedPreferences
    lateinit var descprefEditor: SharedPreferences.Editor
    var myLat: Double? = null
    var myLon: Double? = null
    var mLocationPermissionGranted: Boolean = false
    //var isLocTaken: Boolean
    private var locationManager: LocationManager? = null

>>>>>>> Stashed changes
    override fun getCount(): Int {
        return 20
    }

    override fun getItem(position: Int): String {
        return mData[position].url
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

<<<<<<< Updated upstream
=======
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

>>>>>>> Stashed changes
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view =
            LayoutInflater.from(parent!!.context).inflate(R.layout.tinder_card, parent, false)
        val imgView = view.findViewById<ImageView>(R.id.image_view)
<<<<<<< Updated upstream
        Log.e("adapter", mData.toString())
        var descFragment=DescriptionFragment()
        descFragment.isHidden
        Glide.with(parent.context).load(mData[position].url).into(imgView)
=======
        val placeName = view.findViewById<TextView>(R.id.textViewCard)
        val title = view.findViewById<TextView>(R.id.sights_desc_text_view)
        val textDist = view.findViewById<TextView>(R.id.text_distance)
        val moreInfoBTN: Button = view.findViewById(R.id.more_info_btn)

        var lat = mData[position].placeGeoX!!.toDouble()
        var lng = mData[position].placeGeoY!!.toDouble()
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
            Log.e("PERMISSION", mLocationPermissionGranted.toString())
            var locationManager: LocationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                0f,
                object : android.location.LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        myLat = location!!.latitude
                        myLon = location!!.longitude
                        Log.e("COORDS", "$myLat $myLon")
                        var dist = coordToMeters(myLat!!, lat, myLon!!, lng)

                        textDist.text = formDist(dist)


                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    }

                    override fun onProviderEnabled(provider: String?) {
                    }

                    override fun onProviderDisabled(provider: String?) {
                    }

                })

        } else {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
        }


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
            Glide.with(parent.context).load(mData[position].placeImg).into(imgView)
        }
        descpref=(context as MainActivity).getSharedPreferences("DESCRIPTION",MODE_PRIVATE)
        moreInfoBTN.setOnClickListener() {
            val moreInfoFragment = DescriptionFragment()
            descprefEditor=descpref.edit()
            descprefEditor.putString("DESC",mData[position].description)
            descprefEditor.apply()
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, moreInfoFragment).commit()
        }



>>>>>>> Stashed changes

        return view
    }

}