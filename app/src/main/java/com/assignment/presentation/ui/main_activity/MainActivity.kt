package com.assignment.presentation.ui.main_activity

import android.os.Bundle
import androidx.activity.viewModels
import com.assignment.R
import com.assignment.databinding.MainActivityBinding
import com.assignment.presentation.base.BaseActivity
import com.assignment.presentation.ui.home.HomeFragment.Companion.newInstance
import com.assignment.presentation.ui.repo_listing.RepoListingFragment.Companion.repoListFragment
import com.assignment.presentation.utilities.addFragmentByTag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun subscribeToObservers() {
        viewModel.redirectToRepoListing.observe(this@MainActivity) {
            supportFragmentManager.addFragmentByTag(repoListFragment(it), "RepoFragment")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, newInstance(), "HomeFragment").commit()
//        if (isInternetConnected()) {
//            viewModel.getListData()
//        } else {
//              viewModel.getAllUsers()
//        }
    }

    override fun getViewBinding(): MainActivityBinding = MainActivityBinding.inflate(layoutInflater)

}