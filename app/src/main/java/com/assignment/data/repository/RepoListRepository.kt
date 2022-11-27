package com.assignment.data.repository

import com.assignment.data.repository.base.BaseApiRepository

class RepoListRepository : BaseApiRepository() {

    suspend fun getRepoList(query:String,pageNo:Int) =
        parseResult(api.getRepoList(query,pageNo)) { response -> response}
}