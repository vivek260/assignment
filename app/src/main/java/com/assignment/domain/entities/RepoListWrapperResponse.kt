package com.assignment.domain.entities

import com.assignment.presentation.model.RepoListResponse
import com.google.gson.annotations.SerializedName

class RepoListWrapperResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("git_url") val git_url: String
    ){
    fun toRepoList()=RepoListResponse(id, node_id, name, description, created_at, git_url)
}