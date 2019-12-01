package com.example.putinder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Main2Activity : AppCompatActivity() {

    lateinit var descpref: SharedPreferences
    lateinit var descprefEditor: SharedPreferences.Editor
    var myLat: Double? = null
    var myLon: Double? = null
    var mLocationPermissionGranted: Boolean = false
    private var locationManager: LocationManager? = null
    var flag:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        /*if (ContextCompat.checkSelfPermission(
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
                        if (myLat!=null && myLon!=null && flag!=true){
                            descpref = getSharedPreferences("DESCRIPTION",
                                Context.MODE_PRIVATE
                            )

                            descprefEditor = descpref.edit()
                            descprefEditor.putFloat("myLat", myLat!!.toFloat())
                            descprefEditor.putFloat("myLon",myLon!!.toFloat())
                            descprefEditor.apply()


                            var intent = Intent(applicationContext,MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()

                        }
                        Log.e("COORDS", "$myLat $myLon")



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
        }*/



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
                        if (myLat!=null && myLon!=null && flag!=true){
                            descpref = getSharedPreferences("DESCRIPTION",
                                Context.MODE_PRIVATE
                            )

                            descprefEditor = descpref.edit()
                            descprefEditor.putFloat("myLat", myLat!!.toFloat())
                            descprefEditor.putFloat("myLon",myLon!!.toFloat())
                            descprefEditor.apply()


                            var intent = Intent(applicationContext,MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()

                        }
                        Log.e("COORDS", "$myLat $myLon")



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
