package com.assignment.presentation

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssignmentApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AssignmentApplication? = null

        fun applicationContext(): Context {
            return instance?.applicationContext!!
        }
    }
}