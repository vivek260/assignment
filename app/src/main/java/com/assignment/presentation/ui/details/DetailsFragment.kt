package com.assignment.presentation.ui.details


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.databinding.FragmentDetailsBinding
import com.assignment.presentation.base.BaseFragment
import com.assignment.presentation.model.RepoListResponse
import com.assignment.presentation.ui.details.adapter.ContributorsListAdapter
import com.assignment.presentation.ui.main_activity.MainActivityViewModel
import com.assignment.presentation.utilities.Imagify.loadCircularImage
import com.assignment.presentation.utilities.Imagify.loadImage
import com.assignment.presentation.utilities.Utils.Companion.makeGone
import com.assignment.presentation.utilities.Utils.Companion.makeVisible

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private lateinit var viewModel: DetailsViewModel

    companion object {
        private var repo: RepoListResponse? = null
        fun newDetailFragment(data: RepoListResponse): DetailsFragment {
            repo = data
            return DetailsFragment()
        }
    }

    override fun subscribeToObservers() {
        viewModel.apply {
            contributorsList.observe(this@DetailsFragment){
                binding.rvContributors.layoutManager = LinearLayoutManager(context)
                binding.rvContributors.isNestedScrollingEnabled = false;
                binding.rvContributors.adapter = ContributorsListAdapter(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        repo?.let {
            binding.tvTitle.text = it.name
            if (it.description == null) {
                binding.tvPDescription.makeGone()
                binding.tvDescription.makeGone()
            } else {
                binding.tvDescription.text = it.description
            }
            loadImage(binding.ivProfile, it.owner.avatar_url)
            binding.tvOwnerName.text = it.owner.login
            binding.tvProjectLink.text = it.html_url
            binding.tvProjectLink.movementMethod = LinkMovementMethod.getInstance()
        }
        binding.tvProjectLink.setOnClickListener {
            binding.webview.makeVisible()
            binding.webview.settings.setJavaScriptEnabled(true)
            binding.webview.webViewClient = WebViewClient()
            repo?.html_url?.let { it1 -> binding.webview.loadUrl(it1) }
            binding.webview.settings.setSupportZoom(true)
        }
        repo?.contributors_url?.let { viewModel.getContributors(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)

}