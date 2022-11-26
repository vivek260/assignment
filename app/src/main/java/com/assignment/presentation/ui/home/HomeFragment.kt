package com.assignment.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.assignment.databinding.FragmentHomeBinding
import com.assignment.presentation.base.BaseFragment
import com.assignment.presentation.model.RepoListResponse
import com.assignment.presentation.ui.main_activity.MainActivityViewModel
import com.assignment.presentation.utilities.Utils.Companion.makeGone
import com.assignment.presentation.utilities.Utils.Companion.makeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var viewModel: HomeViewModel
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private var listItems: List<RepoListResponse>? = null
    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun subscribeToObservers() {
        viewModel.apply {
            repoList.observe(this@HomeFragment) {
                listItems = it
                redirectToListFragment()
            }
            repoFromDatabase.observe(viewLifecycleOwner) {
                listItems = it.map { item ->
                    RepoListResponse(
                        item.id,
                        item.node_id,
                        item.name,
                        item.description,
                        item.created_at,
                        item.git_url
                    )
                }
                redirectToListFragment()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.btnSearch.setOnClickListener {
            val searchText: String = binding.etOwner.text.toString()
            if (searchText.trim().isNotEmpty()) {
                binding.loader.loader.makeVisible()
                viewModel.searchRepoList(searchText)
            } else {
                Toast.makeText(
                    activity?.applicationContext,
                    "Please enter Owner/Organization! ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun redirectToListFragment(){
        binding.loader.loader.makeGone()
        listItems?.let { mainActivityViewModel.openRepoListing(it) }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}