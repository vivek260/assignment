package com.assignment.di.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.di.entity.RepoEntity

@Database(entities = [RepoEntity::class], version = 1)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun dao():UsersDao
}