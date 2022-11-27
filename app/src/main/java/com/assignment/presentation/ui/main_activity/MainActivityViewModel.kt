package com.assignment.presentation.ui.main_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.presentation.model.RepoListResponse

class MainActivityViewModel : ViewModel() {
    val redirectToRepoListing = MutableLiveData<List<RepoListResponse>>()
    val shareRepo = MutableLiveData<String>()
    val openDetailPage = MutableLiveData<RepoListResponse>()
    var owner:String = ""
    fun openRepoListing(data: List<RepoListResponse>, query: String) {
        owner = query
        redirectToRepoListing.value = data
    }

    fun onShareRepo(slug: String) {
        shareRepo.value = slug
    }

    fun onRepoClick(data: RepoListResponse) {
        openDetailPage.value = data
    }

}