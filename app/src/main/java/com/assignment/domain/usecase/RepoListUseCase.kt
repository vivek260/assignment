package com.assignment.domain.usecase

import com.assignment.data.repository.RepoListRepository
import com.assignment.domain.base.UseCase
import com.assignment.domain.entities.RepoListWrapperResponse
import com.assignment.domain.entities.Result
import com.assignment.presentation.model.RepoListResponse
import com.assignment.presentation.model.RepoResponse

class RepoListUseCase : UseCase<List<RepoListWrapperResponse>>() {

    private val repository by lazy { RepoListRepository() }
    private var repo = ""
    private var page_no =1
    override suspend fun makeRequest(): Result<List<RepoListWrapperResponse>> =
        repository.getRepoList(repo,page_no)


    fun fetchRepoList(
        query:String,
        pageNo:Int,
        success: (List<RepoListWrapperResponse>) -> Unit,
        failure: (Exception) -> Unit
    ) {
        this.repo = query
        this.page_no = pageNo
        execute(success, failure)
    }
}