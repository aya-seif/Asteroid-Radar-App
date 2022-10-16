package com.ayaabdelaziz.asteroidradarapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageOfDay(@PrimaryKey(autoGenerate = true)
                      val id : Long?=0, val media_type: String, val url: String, val title: String)
