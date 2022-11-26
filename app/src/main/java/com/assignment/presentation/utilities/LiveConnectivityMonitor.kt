package com.assignment.presentation.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.assignment.data.NetworkMonitor
import com.assignment.presentation.AssignmentApplication


class LiveConnectivityMonitor : NetworkMonitor {

    override fun isConnected(): Boolean {
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
}