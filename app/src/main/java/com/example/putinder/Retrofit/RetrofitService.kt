package com.example.putinder.retrofit

import com.example.putinder.model.Sights
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET("/photos")
    fun TransferToSightsActivity():retrofit2.Response<List<Sights>>

}