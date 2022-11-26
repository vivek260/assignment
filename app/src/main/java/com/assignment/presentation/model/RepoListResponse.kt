package com.assignment.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
class RepoResponse(
    val data:List<RepoListResponse>
): Parcelable

@Parcelize
class RepoListResponse(
    val id: Long,
    val node_id: String,
    val name: String,
    val description: String?,
    val created_at: String,
    val git_url: String
): Parcelable