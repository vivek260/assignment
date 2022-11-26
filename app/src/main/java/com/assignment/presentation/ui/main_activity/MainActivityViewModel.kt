package com.assignment.presentation.ui.main_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.presentation.model.RepoListResponse

class MainActivityViewModel:ViewModel() {
    val redirectToRepoListing = MutableLiveData<List<RepoListResponse>>()

    fun openRepoListing(data:List<RepoListResponse>) {
        redirectToRepoListing.value = data
    }


}