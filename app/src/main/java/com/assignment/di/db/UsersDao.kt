package com.assignment.di.db

import androidx.room.*
import com.assignment.di.entity.RepoEntity

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertRepo(repoEntity: RepoEntity)

    @Delete
     fun deleteUser(repoEntity: RepoEntity)

    @Update
     fun update(repoEntity: RepoEntity)

    @Query("SELECT * FROM Repo_table ORDER BY id ASC")
    fun getAllRepo(): List<RepoEntity>
}