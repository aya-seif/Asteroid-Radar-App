package com.ayaabdelaziz.asteroidradarapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid
import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay


@Database(entities =[Asteroid::class, ImageOfDay::class] , version = 3)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract fun asteroidDao(): AsteroidDao

    companion object {
        @Volatile
        var instance: AsteroidDatabase? = null

        fun getDatabase(context: Context): AsteroidDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val Instance = Room.databaseBuilder(
                    context.applicationContext,
                    AsteroidDatabase::class.java,
                    "asteroid_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                instance = Instance
                return Instance

            }

        }
    }
}