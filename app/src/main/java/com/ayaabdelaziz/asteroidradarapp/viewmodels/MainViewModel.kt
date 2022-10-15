package com.ayaabdelaziz.asteroidradarapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid
import com.ayaabdelaziz.asteroidradarapp.repository.MainRepository
import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay
import com.ayaabdelaziz.asteroidradarapp.utils.Constants
import com.ayaabdelaziz.asteroidradarapp.utils.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel : ViewModel() {

    private val imageMutableLiveData = MutableLiveData<ImageOfDay>()
    val imageLiveData: LiveData<ImageOfDay> = imageMutableLiveData


    private val asteroidMutableLiveData = MutableLiveData<ArrayList<Asteroid>>()
    val asteroidLiveData: LiveData<ArrayList<Asteroid>> = asteroidMutableLiveData

    private var mainRepository: MainRepository = MainRepository()

    init {
        getImageOfDay()
        getAseroids()
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
}
