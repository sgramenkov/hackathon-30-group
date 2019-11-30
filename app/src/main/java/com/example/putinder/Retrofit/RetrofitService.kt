package com.example.putinder.retrofit

import com.example.putinder.retrofit.Models.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("/photos")
    suspend fun TransferToPhotosActivity(): Response<List<Photos>>
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 84904c6... awdad
    @POST
    suspend fun postData(){
>>>>>>> parent of 84904c6... awdad

}