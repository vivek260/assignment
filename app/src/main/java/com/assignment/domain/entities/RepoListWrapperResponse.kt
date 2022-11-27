package com.assignment.domain.entities

import com.google.gson.annotations.SerializedName

data class RepoListWrapperResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("contributors_url") val contributors_url:String,
    @SerializedName("owner") val owner:OwnerWrapperResponse
    )

data class OwnerWrapperResponse(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar_url: String
)