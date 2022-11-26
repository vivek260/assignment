package com.assignment.data.repository

import com.assignment.data.repository.base.BaseApiRepository
import com.assignment.presentation.model.RepoListResponse
import com.assignment.presentation.model.RepoResponse

class RepoListRepository : BaseApiRepository() {

    suspend fun getRepoList(query:String,pageNo:Int) =
        parseResult(api.getList()) { response -> response}
}