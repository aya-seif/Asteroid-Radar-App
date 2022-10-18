package com.ayaabdelaziz.asteroidradarapp.viewmodels

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.ayaabdelaziz.asteroidradarapp.database.AsteroidDatabase
import com.ayaabdelaziz.asteroidradarapp.database.ImageOfTheDay
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid
import com.ayaabdelaziz.asteroidradarapp.repository.MainRepository
import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay
import com.ayaabdelaziz.asteroidradarapp.utils.Constants
import com.ayaabdelaziz.asteroidradarapp.utils.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(application: Application) : AndroidViewModel(application) {


    val database = AsteroidDatabase.getDatabase(application)
    val repository = MainRepository(database)
    lateinit var imageLiveDataDB: LiveData<ImageOfDay>
    var _asteroidLiveData: LiveData<List<Asteroid>> = repository.asteroidLiveDataDB
    val asteroidForToday: LiveData<List<Asteroid>> = repository.asteroidForToday
    val asteroidForWeek: LiveData<List<Asteroid>> = repository.asteroidForWeek

    val mediatorLiveData = MediatorLiveData<List<Asteroid>>()


    init {
        getImageOfDayFromRoom()
        mediatorLiveData.addSource(_asteroidLiveData) {
            mediatorLiveData.value = it
        }

    }

    private fun getImageOfDayFromRoom() {
        viewModelScope.launch {
            try {
                imageLiveDataDB = database.asteroidDao().getImageOfDay()
            } catch (e: Exception) {
                Log.d("TAG", "getImageOfDayFromRoom:${e.message.toString()} ")
            }
        }
    }

    fun displayAsteroidForToday() {
        mediatorLiveData.removeSource(_asteroidLiveData)
        mediatorLiveData.addSource(asteroidForToday) {
            Log.d("TAG", "displayAsteroidForToday:${Constants.getCurrentDate()} ")
            mediatorLiveData.value = it
        }
    }

    fun displaySavedAsteroid() {
        mediatorLiveData.removeSource(_asteroidLiveData)
        mediatorLiveData.removeSource(asteroidForToday)
        mediatorLiveData.addSource(_asteroidLiveData) {
            mediatorLiveData.value = it
        }
    }

    fun displayAsteroidForWeek() {
        mediatorLiveData.removeSource(_asteroidLiveData)
        mediatorLiveData.removeSource(asteroidForToday)
        mediatorLiveData.addSource(asteroidForWeek) {
            mediatorLiveData.value = it
        }

    }


}
