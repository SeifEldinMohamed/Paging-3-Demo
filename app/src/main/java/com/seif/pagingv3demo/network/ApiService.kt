package com.seif.pagingv3demo.network

import com.seif.pagingv3demo.model.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getDataFromApi(@Query("page") page:Int):Data // page number as query parameter

}