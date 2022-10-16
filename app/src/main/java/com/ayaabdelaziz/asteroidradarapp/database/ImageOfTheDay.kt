package com.ayaabdelaziz.asteroidradarapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey



data class ImageOfTheDay(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val media_type: String, val url: String, val title: String
)