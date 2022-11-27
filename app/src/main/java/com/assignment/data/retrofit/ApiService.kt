package com.assignment.data.retrofit

import com.assignment.domain.entities.ContributorsWrapperResponse
import com.assignment.domain.entities.RepoListWrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("users/{owner}/repos?per_page=10")
    suspend fun getRepoList(@Path("owner") query:String, @Query("page") page:Int): Response<List<RepoListWrapperResponse>>

    @GET("users/Ayushparikh-code/repos?per_page=10&page=1")
    suspend fun getList(): Response<List<RepoListWrapperResponse>>

    @GET
    suspend fun getContributorsList(@Url url:String):Response<List<ContributorsWrapperResponse>>

}