package com.assignment.presentation.ui.main_activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.assignment.databinding.MainActivityBinding
import com.assignment.di.entity.RepoEntity
import com.assignment.presentation.base.BaseActivity
import com.assignment.presentation.model.RepoListResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {
    private val viewModel: MainActivityViewModel by viewModels()

    private var listItems: List<RepoListResponse>? = null

    override fun subscribeToObservers() {
        viewModel.apply {
            repoList.observe(this@MainActivity) {
                listItems = it
                setupAdapter()
            }
            repoFromDatabase.observe(this@MainActivity) {
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
                setupAdapter()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnSearch.setOnClickListener {
            val searchText: String = binding.etOwner.text.toString()
            if (searchText.trim().isNotEmpty()) {
                viewModel.searchRepoList(searchText)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please enter Owner/Organization! ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
//        if (isInternetConnected()) {
//            viewModel.getListData()
//        } else {
//              viewModel.getAllUsers()
//        }
    }

    private fun setupAdapter() {
//        binding.rvlist.layoutManager = LinearLayoutManager(this@MainActivity)
//        binding.rvlist.adapter =
//            listItems?.let { UserAdapter(it) }
    }

    override fun getViewBinding(): MainActivityBinding = MainActivityBinding.inflate(layoutInflater)

}