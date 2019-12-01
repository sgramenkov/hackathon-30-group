package com.example.putinder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var descpref: SharedPreferences
    lateinit var descprefEditor: SharedPreferences.Editor
    var myLat: Double? = null
    var myLon: Double? = null
    var mLocationPermissionGranted: Boolean = false
    private var locationManager: LocationManager? = null
    var flag:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news,
                R.id.navigation_chat,
                R.id.navigation_tinder,
                R.id.navigation_favorite,
                R.id.navigation_profile
            )
        )
        navView.setupWithNavController(navController)

    }

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
            Log.e("PERMISSION", mLocationPermissionGranted.toString())
            var locationManager: LocationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                0f,
                object : android.location.LocationListener {
                    override fun onLocationChanged(location: Location?) {

                        myLat = location!!.latitude
                        myLon = location!!.longitude
                        if (myLat!=null && myLon!=null){
                            descpref = getSharedPreferences("DESCRIPTION",
                                Context.MODE_PRIVATE
                            )

                            descprefEditor = descpref.edit()
                            descprefEditor.putFloat("myLat", myLat!!.toFloat())
                            descprefEditor.putFloat("myLon",myLon!!.toFloat())
                            descprefEditor.apply()

                        }
                        Log.e("coordsResume", "$myLat $myLon")



                        flag=true


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
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
        }


    }
}
