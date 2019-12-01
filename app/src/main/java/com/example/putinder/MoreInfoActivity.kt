package com.example.putinder

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MoreInfoActivity : AppCompatActivity() {
    lateinit var descpref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)
        val sightIMG:ImageView=findViewById(R.id.sight_img)
        descpref=getSharedPreferences("DESCRIPTION", MODE_PRIVATE)
        Log.e("DESC",descpref.getString("DESC",""))
        val descTV:TextView=findViewById(R.id.desc_text_view)
        descTV.text=descpref.getString("DESC","")
        val sightsName:TextView=findViewById(R.id.name_more_info_tv)
        sightsName.text=descpref.getString("name","")
        val sightsCategory:TextView=findViewById(R.id.category_more_info)
        sightsCategory.text=descpref.getString("category","")
        Glide.with(this).load(descpref.getString("photo","")).into(sightIMG)
    }
}
