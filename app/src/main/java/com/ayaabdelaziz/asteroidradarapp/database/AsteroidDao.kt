package com.ayaabdelaziz.asteroidradarapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid
import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay


@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAsteroid(arr: ArrayList<Asteroid>)


    @Query("SELECT * FROM asteroid ORDER BY id ASC")
    fun getAllAsteroids(): LiveData<List<Asteroid>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageOfDay(imageOfDay: ImageOfDay)

    @Query("SELECT * FROM imageofday")
    fun getImageOfDay(): LiveData<ImageOfDay>


}