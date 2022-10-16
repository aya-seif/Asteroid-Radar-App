package com.ayaabdelaziz.asteroidradarapp.viewmodels

import android.app.Application
import android.util.Log
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


    private var asteroidDao = AsteroidDatabase.getDatabase(application).asteroidDao()
    lateinit var asteroidLiveDataDB: LiveData<List<Asteroid>>
    lateinit var imageLiveDataDB: LiveData<ImageOfDay>


    //sh3allll
    private val imageMutableLiveData = MutableLiveData<ImageOfDay>()
    val imageLiveData: LiveData<ImageOfDay> = imageMutableLiveData
    private val asteroidMutableLiveData = MutableLiveData<ArrayList<Asteroid>>()
    val asteroidLiveData: LiveData<ArrayList<Asteroid>> = asteroidMutableLiveData
    private var mainRepository: MainRepository = MainRepository()

    init {

        if(Constants.checkForInternet(application)){
            insertAsteroidsToDatabase()
            insertImageOfDay()
        }
        getImageOfDayFromRoom()
        getAllAsteroidFromRoom()

    }

    fun getImageOfDay() {
        viewModelScope.launch {
            val obj = mainRepository.getImageOfDay()
            imageMutableLiveData.value = obj
        }
    }

    fun getAseroids() {
        viewModelScope.launch {
            val response = mainRepository.getAsteroids()
            if (!response.isNullOrEmpty()){
                asteroidMutableLiveData.value = response
            }
        }
    }



    fun insertAsteroidsToDatabase() {

        viewModelScope.launch {
            val data = mainRepository.getAsteroids()
            asteroidDao.insertAllAsteroid(data)
            Log.d("TAG", "insertAsteroidsToDatabase: saved successfully ")

        }
    }
    fun insertImageOfDay() {
        viewModelScope.launch {
            val data = mainRepository.getImageOfDay()
            asteroidDao.insertImageOfDay(data)
            Log.d("TAG", "insertImageOfDay: saved successfully ")
        }
    }

    fun getAllAsteroidFromRoom() {
        viewModelScope.launch {
            asteroidLiveDataDB = asteroidDao.getAllAsteroids()

        }
    }
    fun getImageOfDayFromRoom() {
        viewModelScope.launch {
            try {
                imageLiveDataDB = asteroidDao.getImageOfDay()
            } catch (e: Exception) {
                Log.d("TAG", "getImageOfDayFromRoom:${e.message.toString()} ")
            }
        }

    }
}
