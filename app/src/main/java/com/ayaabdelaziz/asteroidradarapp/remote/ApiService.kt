package com.ayaabdelaziz.asteroidradarapp.remote

import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("planetary/apod")
    fun getImageOfDay(@Query("api_key") apikey: String): Deferred<ImageOfDay>

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroid(
        @Query("api_key") apikey: String
    ): String
}