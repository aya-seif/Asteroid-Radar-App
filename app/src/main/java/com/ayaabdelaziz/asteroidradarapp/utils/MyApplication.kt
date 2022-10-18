package com.ayaabdelaziz.asteroidradarapp.utils

import android.app.Application
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    val scope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        launchWork()
        super.onCreate()
    }

    fun launchWork() {
        scope.launch {
            setRequestWorker()
        }
    }
    fun setRequestWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val sendWorkRequest =
            PeriodicWorkRequestBuilder<DataRefreshWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork("work", ExistingPeriodicWorkPolicy.KEEP, sendWorkRequest)

    }

}