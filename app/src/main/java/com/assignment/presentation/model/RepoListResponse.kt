package com.assignment.presentation.model

import android.os.Parcelable
import com.assignment.domain.entities.OwnerWrapperResponse
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class RepoListResponse(
    val id: Long,
    val node_id: String,
    val name: String,
    val description: String?,
    val updated_at: String,
    val html_url: String,
    val contributors_url: String,
    val owner: OwnerResponse
) : Parcelable

@Parcelize
class OwnerResponse(
    val login: String,
    val avatar_url: String
) : Parcelable