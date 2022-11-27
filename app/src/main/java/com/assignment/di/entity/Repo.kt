package com.assignment.di.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.presentation.model.OwnerResponse

@Entity(tableName = "Repo_table")
data class RepoEntity(
    @PrimaryKey()
    val id: Long,
    val node_id: String,
    val name: String,
    val description: String,
    val created_at: String,
    val git_url: String,
    val contributors_url:String
)