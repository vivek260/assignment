package com.assignment.presentation.ui.repo_listing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.databinding.FragmentRepoListingBinding
import com.assignment.presentation.base.BaseFragment
import com.assignment.presentation.helper.PaginationScrollListener
import com.assignment.presentation.model.RepoListResponse
import com.assignment.presentation.ui.main_activity.MainActivityViewModel
import com.assignment.presentation.ui.repo_listing.repo_adapter.RepoListingAdapter

class RepoListingFragment : BaseFragment<FragmentRepoListingBinding>() {
    private lateinit var viewModel: RepoListingViewModel
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private var scrollListener: PaginationScrollListener? = null
    private var adapter: RepoListingAdapter? = null

    companion object {
        private var repoList= ArrayList<RepoListResponse>()
        private var owner: String = ""
        fun repoListFragment(data: List<RepoListResponse>, query: String): RepoListingFragment {
            repoList?.addAll(data)
            owner = query
            return RepoListingFragment()
        }
    }

    override fun subscribeToObservers() {
        viewModel.apply {
            repositoryList.observe(this@RepoListingFragment) {
                repoList.addAll(it)
                adapter?.notifyDataSetChanged()
                scrollListener?.loadCompleted()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.rvRepoList.layoutManager = LinearLayoutManager(context)
        binding.rvRepoList.isNestedScrollingEnabled = false;
            adapter = repoList?.let {
            context?.let { mContext ->
                RepoListingAdapter(
                    it,
                    mContext, mainActivityViewModel
                )
            }
        }
        binding.rvRepoList.adapter = adapter
        initPaginationListener(binding.rvRepoList.layoutManager as LinearLayoutManager)
    }

    private fun initPaginationListener(layoutManager: LinearLayoutManager) {
        scrollListener = object : PaginationScrollListener(layoutManager) {

            override fun loadMore() = viewModel.searchRepoList(owner)

            override fun isAllLoaded() = viewModel.isLastPageNumber()
        }
        scrollListener?.let { binding.rvRepoList.addOnScrollListener(it) }
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