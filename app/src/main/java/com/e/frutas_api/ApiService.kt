package com.e.frutas_api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(value = "all")
    suspend fun getAllFruits():Response<MutableList<FruitResponse>>

}