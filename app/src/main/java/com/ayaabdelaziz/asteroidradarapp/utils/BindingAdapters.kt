package com.ayaabdelaziz.asteroidradarapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayaabdelaziz.asteroidradarapp.R
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid
import com.ayaabdelaziz.asteroidradarapp.pojo.ImageOfDay
import com.ayaabdelaziz.asteroidradarapp.ui.AsteroidAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadImage")
fun ImageView.loadImageUri(imageOfDay: ImageOfDay?) {
    if (imageOfDay != null) {
        Picasso.get().load(imageOfDay.url).fit().into(this);
    }
}


@BindingAdapter("loaddata")
fun RecyclerView.loaddata(data: List<Asteroid>?) {
    val adapter = this.adapter as AsteroidAdapter
    adapter.submitList(data)
}

@BindingAdapter("seticon")
fun ImageView.setIcon(isPotentiallyHazardous: Boolean) {
    if (isPotentiallyHazardous) {
        this.setImageResource(R.drawable.ic_status_potentially_hazardous)
        this.contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        this.setImageResource(R.drawable.ic_status_normal)
        this.contentDescription = context.getString(R.string.not_hazardous_asteroid_image)

    }
}



@BindingAdapter("isPotentiallyHazardousImg")
fun ImageView.setPropperImg(isPotentiallyHazardous: Boolean) {
    if (isPotentiallyHazardous) {
        this.setImageResource(R.drawable.asteroid_hazardous)
        this.contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        this.setImageResource(R.drawable.asteroid_safe)
        this.contentDescription = context.getString(R.string.not_hazardous_asteroid_image)

    }
}

@BindingAdapter("absolute_magnitude_text")
fun TextView.absoluteMagitudeConvert(absolute_magnitude: Double) {

    this.text =
        String.format(resources.getString(R.string.astronomical_unit_format), absolute_magnitude)
}

@BindingAdapter("estimated_diameterTokm")
fun TextView.estimatedDiameterTokm(estimated: Double) {

    this.text = String.format(resources.getString(R.string.km_unit_format), estimated)
}

@BindingAdapter("relative_velocity")
fun TextView.relative_velocity(relative_velocity: Double) {
    this.text = String.format(resources.getString(R.string.km_s_unit_format), relative_velocity)
}

