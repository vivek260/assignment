package com.assignment.presentation.utilities

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.assignment.R
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