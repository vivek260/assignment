package com.assignment.data.repository

import com.assignment.data.repository.base.BaseApiRepository

class ContributorsRepository : BaseApiRepository() {
    suspend fun getContributorsList(url: String) =
        parseResult(api.getContributorsList(url)) { response -> response }
}