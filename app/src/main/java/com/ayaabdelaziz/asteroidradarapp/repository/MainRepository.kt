package com.ayaabdelaziz.asteroidradarapp.repository


import android.util.Log
import androidx.lifecycle.LiveData
import com.ayaabdelaziz.asteroidradarapp.database.AsteroidDatabase
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid
import com.ayaabdelaziz.asteroidradarapp.remote.RetrofitClient
import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay
import com.ayaabdelaziz.asteroidradarapp.utils.Constants
import com.ayaabdelaziz.asteroidradarapp.utils.parseAsteroidsJsonResult
import org.json.JSONObject

class MainRepository(private val database: AsteroidDatabase) {

    private val apiService = RetrofitClient.apiService
    val asteroidLiveDataDB: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getAllAsteroids()
    val asteroidForToday: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getAsteroidofToday(Constants.getCurrentDate())
    val asteroidForWeek: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getAsteroidforWeek()


    private suspend fun getImageOfDay(): ImageOfDay {
        val imageOfDay: ImageOfDay?
        val api = apiService.getImageOfDay(Constants.API_KEY).await()
        imageOfDay = if (api.media_type == "image") {
            api
        } else {
            null
        }
        return imageOfDay!!
    }

    private suspend fun getAsteroids(): ArrayList<Asteroid> {
        val arr: ArrayList<Asteroid>
        val result = apiService.getAsteroid(Constants.API_KEY)
        val json = JSONObject(result)
        arr = parseAsteroidsJsonResult(json)
        return arr
    }

    suspend fun insertAsteroidsToDatabase() {
        val data = getAsteroids()
        database.asteroidDao().insertAllAsteroid(data)
        Log.d("TAG", "insertAsteroidsToDatabase: saved successfully ")
    }

    suspend fun insertImageOfDay() {
        val data = getImageOfDay()
        database.asteroidDao().insertImageOfDay(data)
        Log.d("TAG", "insertImageOfDay: saved successfully ")

    }


}