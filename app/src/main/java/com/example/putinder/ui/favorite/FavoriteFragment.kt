package com.example.putinder.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Photos
import com.example.putinder.ui.adapters.FavAdapter
import io.realm.Realm
import io.realm.RealmConfiguration

class FavoriteFragment:Fragment() {
    lateinit var realm: Realm
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)

        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .name("PhotosDB.realm")
            .build()
        realm = Realm.getInstance(config)

        val recycler = root.findViewById<RecyclerView>(R.id.fav_recycler)
        recycler.layoutManager = LinearLayoutManager(context)
        var favList = getData()
        recycler.adapter = FavAdapter(favList,context!!)


        return root
    }
    private fun getData(): List<Photos> {
        var list: ArrayList<Photos> = arrayListOf()
        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .name("PhotosDB.realm")
            .build()
        realm = Realm.getInstance(config)
        val realmResult = realm.where(Photos::class.java).findAll()
        if (realmResult != null) {
            for(i in realmResult.indices){
                val tempDataPhoto = Photos(realmResult[i]!!.id,realmResult[i]!!.albumId, realmResult[i]!!.url, realmResult[i]!!.title)
                list.add(tempDataPhoto)
            }
        } else {
            Toast.makeText(context, "Realm error!", Toast.LENGTH_SHORT).show()
        }
        return list
    }
}