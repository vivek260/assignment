package com.assignment.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.assignment.presentation.model.ViewModelCreator


abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    lateinit var binding: B

    companion object {
        var isInForeground = false
    }

    abstract fun subscribeToObservers()

    abstract fun getViewBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        subscribeToObservers()
    }

    override fun onResume() {
        super.onResume()
        isInForeground = true
    }
}
