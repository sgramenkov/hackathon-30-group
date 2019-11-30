package com.example.putinder.retrofit.Models

/*import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Photos (@PrimaryKey
                   var id: Long = 0,
                   var albumId: Int? = 1,
                   var url: String? = null):RealmObject()*/
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Photos (@PrimaryKey
                   var id:Int?=null,
                   var albumId:Int = 1,
                   var url:String?=null,
                   var title:String?=null):RealmObject()