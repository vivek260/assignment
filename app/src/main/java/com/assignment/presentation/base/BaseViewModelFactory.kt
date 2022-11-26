package com.assignment.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//class BaseViewModelFactory(private val v: ViewModel): ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(v::class.java)) {
//            return v as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}