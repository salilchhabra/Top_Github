package com.example.topgithub.service

import com.example.topgithub.model.GithubResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("repo")
    fun getGithubRepos(
        @Query("lang") lang: String,
        @Query("since") since: String = "weekly"
    ): Deferred<GithubResponse>
}

