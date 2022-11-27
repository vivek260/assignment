package com.assignment.presentation.ui.main_activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.assignment.R
import com.assignment.databinding.MainActivityBinding
import com.assignment.presentation.base.BaseActivity
import com.assignment.presentation.ui.details.DetailsFragment
import com.assignment.presentation.ui.details.DetailsFragment.Companion.newDetailFragment
import com.assignment.presentation.ui.home.HomeFragment.Companion.newInstance
import com.assignment.presentation.ui.repo_listing.RepoListingFragment.Companion.repoListFragment
import com.assignment.presentation.utilities.addFragmentByTag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {

    private val viewModel: MainActivityViewModel by viewModels()
    override fun subscribeToObservers() {
        viewModel.apply {
            redirectToRepoListing.observe(this@MainActivity) {
                supportFragmentManager.addFragmentByTag(repoListFragment(it, this.owner), "RepoFragment")
            }
            shareRepo.observe(this@MainActivity) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Checkout this amazing repo at Github $it")
                startActivity(Intent.createChooser(intent, "Choose the App"))
            }
            openDetailPage.observe(this@MainActivity) {
                supportFragmentManager.addFragmentByTag(newDetailFragment(it), "DetailFragment")
            }
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