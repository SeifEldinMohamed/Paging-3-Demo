package com.seif.pagingv3demo.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getDataFromApi(@Query("page") query:Int) // page number as query parameter
}