package com.assignment.presentation.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.domain.entities.ContributorsWrapperResponse
import com.assignment.domain.entities.RepoListWrapperResponse
import com.assignment.domain.usecase.ContributorsUseCase
import com.assignment.domain.usecase.RepoListUseCase
import com.assignment.presentation.model.ContributorsResponse
import com.assignment.presentation.model.OwnerResponse
import com.assignment.presentation.model.RepoListResponse

class DetailsViewModel : ViewModel() {
    private val useCase by lazy { ContributorsUseCase() }
    val contributorsList = MutableLiveData<List<ContributorsResponse>>()
    val onError = MutableLiveData<Boolean>()

    fun getContributors(url: String) {
        useCase.fetchContributorsList(url, ::onApiSuccess, ::onApiFailure)
    }

    private fun onApiSuccess(list: List<ContributorsWrapperResponse>) {
        val data = list.map { ContributorsResponse(it.login, it.avatar_url, it.contributions) }
        contributorsList.value = data
    }

    private fun onApiFailure(exception: Exception) {
        onError.value = true
    }
}