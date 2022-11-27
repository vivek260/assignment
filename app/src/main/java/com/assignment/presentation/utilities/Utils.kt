package com.assignment.presentation.utilities

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.format.DateUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.assignment.R
import com.assignment.presentation.AssignmentApplication
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
        fun Activity.hideKeyboard() {
            try {
                this.currentFocus?.let {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}