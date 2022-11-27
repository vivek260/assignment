package com.assignment.presentation.ui.repo_listing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.domain.entities.RepoListWrapperResponse
import com.assignment.domain.usecase.RepoListUseCase
import com.assignment.presentation.model.OwnerResponse
import com.assignment.presentation.model.RepoListResponse

class RepoListingViewModel : ViewModel() {
    private val repoUseCase by lazy { RepoListUseCase() }
    val repositoryList = MutableLiveData<List<RepoListResponse>>()
    val onError = MutableLiveData<Boolean>()
    var pageNo = 1
    fun searchRepoList(query: String) {
        repoUseCase.fetchRepoList(query, ++pageNo, ::onApiSuccess, ::onApiFailure)
    }

    private fun onApiSuccess(list: List<RepoListWrapperResponse>) {
        val data = list.map {
            RepoListResponse(
                it.id,
                it.node_id,
                it.name,
                it.description,
                it.updated_at,
                it.html_url,
                it.contributors_url,
                it.owner.let { it1 -> OwnerResponse(it1.login, it1.avatar_url) })
        }
        repositoryList.value = data
    }

    private fun onApiFailure(exception: Exception) {
        onError.value = true
    }

    fun isLastPageNumber() = pageNo == 8
}