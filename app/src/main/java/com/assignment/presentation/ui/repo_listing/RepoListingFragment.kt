package com.assignment.presentation.ui.repo_listing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.databinding.FragmentRepoListingBinding
import com.assignment.presentation.base.BaseFragment
import com.assignment.presentation.model.RepoListResponse
import com.assignment.presentation.ui.repo_listing.repo_adapter.RepoListingAdapter

class RepoListingFragment : BaseFragment<FragmentRepoListingBinding>() {
    private lateinit var viewModel: RepoListingViewModel

    companion object {
        private var repoList: List<RepoListResponse>? = null
        fun repoListFragment(data: List<RepoListResponse>): RepoListingFragment {
            repoList = data
            return RepoListingFragment()
        }
    }

    override fun subscribeToObservers() {

    }

    override fun onStart() {
        super.onStart()
        binding.rvRepoList.layoutManager = LinearLayoutManager(context)
        binding.rvRepoList.isNestedScrollingEnabled = false;
        binding.rvRepoList.adapter = repoList?.let { context?.let { it1 ->
            RepoListingAdapter(it,
                it1
            )
        } }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepoListingViewModel::class.java)
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRepoListingBinding = FragmentRepoListingBinding.inflate(layoutInflater)

}