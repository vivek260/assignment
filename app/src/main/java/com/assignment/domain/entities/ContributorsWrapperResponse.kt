package com.assignment.domain.entities

import com.google.gson.annotations.SerializedName

data class ContributorsWrapperResponse(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("contributions") val contributions: String
)