package com.assignment.presentation.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import com.assignment.presentation.AssignmentApplication

class Utils {
    companion object {

        fun isInternetConnected():Boolean{
            val connectivityManager =
                AssignmentApplication.applicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                ) {
                    return true
                }
            }
            return false
        }
        fun View.makeVisible() {
            this.visibility = View.VISIBLE
        }

        fun View.makeGone() {
            this.visibility = View.GONE
        }
    }
}