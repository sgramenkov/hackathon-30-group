package com.example.putinder.Retrofit.Models

/*import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Photos (@PrimaryKey
                   var id: Long = 0,
                   var albumId: Int? = 1,
                   var url: String? = null):RealmObject()*/
data class Photos (val albumId:Int = 1,val url:String)