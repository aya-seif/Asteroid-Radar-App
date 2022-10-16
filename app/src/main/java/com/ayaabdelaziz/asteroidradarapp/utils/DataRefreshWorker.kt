package com.ayaabdelaziz.asteroidradarapp.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class DataRefreshWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {




        return Result.success()
    }
}