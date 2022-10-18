package com.ayaabdelaziz.asteroidradarapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

object Constants {
    val BASE_URL ="https://api.nasa.gov/"
    val API_KEY = "FkQpiiQhly9G10yDg6WSHgpFQ0BnAcl1sRwvcAEE"
    const val DEFAULT_END_DATE_DAYS = 7
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"




    fun checkForInternet(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


    fun getCurrentDate():String{

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        return formatter.format(date)

//        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//        val currentDate = sdf.format(Date())
//        return currentDate
//        val c: Date = Calendar.getInstance().time
//        val df = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
//        } else {
//            TODO("VERSION.SDK_INT < N")
//        }
//        val formattedDate: String = df.format(c)
//        return formattedDate
    }

}