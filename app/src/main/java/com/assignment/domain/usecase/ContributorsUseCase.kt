package com.assignment.domain.usecase

import com.assignment.data.repository.ContributorsRepository
import com.assignment.domain.base.UseCase
import com.assignment.domain.entities.ContributorsWrapperResponse
import com.assignment.domain.entities.Result

class ContributorsUseCase : UseCase<List<ContributorsWrapperResponse>>() {
    private val repository by lazy { ContributorsRepository() }
    private var url: String = ""
    override suspend fun makeRequest(): Result<List<ContributorsWrapperResponse>> {
       return repository.getContributorsList(url)
    }

    fun fetchContributorsList(
        slug:String,
        success: (List<ContributorsWrapperResponse>) -> Unit,
        failure: (Exception) -> Unit
    ) {
        this.url = slug
        execute(success, failure)
    }
}