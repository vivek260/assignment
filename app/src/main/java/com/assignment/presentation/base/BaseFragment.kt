package com.assignment.presentation.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.assignment.presentation.model.ViewModelCreator

abstract class BaseFragment<M : ViewModel, B : ViewBinding> : Fragment() {

    lateinit var viewModel: M

    private var _binding: B? = null

    val binding get() = _binding!!

    abstract fun subscribeToObservers()

    abstract fun createViewModel(): ViewModelCreator<M>

    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(inflater, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        generateViewModel()
        subscribeToObservers()
    }

    private fun generateViewModel() {
        val creator = createViewModel()
        viewModel = ViewModelProviders.of(this, creator.factory).get(creator.type)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
