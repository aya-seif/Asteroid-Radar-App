package com.ayaabdelaziz.asteroidradarapp.repository


import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid
import com.ayaabdelaziz.asteroidradarapp.remote.RetrofitClient
import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay
import com.ayaabdelaziz.asteroidradarapp.utils.Constants
import com.ayaabdelaziz.asteroidradarapp.utils.parseAsteroidsJsonResult
import org.json.JSONObject

class MainRepository {


    private val apiService = RetrofitClient.apiService;
    suspend fun getImageOfDay(): ImageOfDay {
        val imageOfDay: ImageOfDay?
        val api = apiService.getImageOfDay(Constants.API_KEY).await()
        if (api.media_type == "image") {
            imageOfDay = api
        } else {
            imageOfDay = null
        }
        return imageOfDay!!
    }

    suspend fun getAsteroids(): ArrayList<Asteroid> {
        var arr: ArrayList<Asteroid> = ArrayList<Asteroid>()
        val result = apiService.getAsteroid(Constants.API_KEY)
        val json = JSONObject(result)
        arr = parseAsteroidsJsonResult(json)
        return arr
    }

}