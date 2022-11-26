package com.assignment.di.module

import android.content.Context
import androidx.room.Room
import com.assignment.di.db.RepoDatabase
import com.assignment.di.db.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideUsersDatabase(@ApplicationContext app: Context):RepoDatabase = Room.databaseBuilder(
        app,
        RepoDatabase::class.java,
        "users"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: RepoDatabase):UsersDao = db.dao()

}