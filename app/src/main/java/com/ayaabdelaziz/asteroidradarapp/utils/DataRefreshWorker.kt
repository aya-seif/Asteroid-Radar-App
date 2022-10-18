package com.ayaabdelaziz.asteroidradarapp.utils

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ayaabdelaziz.asteroidradarapp.database.AsteroidDatabase
import com.ayaabdelaziz.asteroidradarapp.repository.MainRepository

class DataRefreshWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getDatabase(applicationContext)
        val mainRepository = MainRepository(database)
        mainRepository.insertImageOfDay()
        mainRepository.insertAsteroidsToDatabase()
        return Result.success()
    }
}