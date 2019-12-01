package com.example.putinder.retrofit.Models



import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Sights (@PrimaryKey
                   var id:Int?=null,
                   var placeName:String?=null,
                   var description:String?=null,
                   var placeGeoX:Float?=null,
                   var placeGeoY:Float?=null,
                   var placeImg:String?=null,
                   var rating:Float?=null,
                   var city:String?=null,
                   var typePlace:String?=null,
                   var moreDescription:String?=null,
                   var free:Boolean?=null
                   ):RealmObject()
