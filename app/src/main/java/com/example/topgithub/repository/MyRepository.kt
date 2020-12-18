package com.example.topgithub.repository
import com.example.topgithub.base.Result
import com.example.topgithub.helper.RetrofitClient

class MyRepository {

    suspend fun getGithubRepo(lang: String) =
        try {
            val result = RetrofitClient.getApi()?.getGithubRepos(lang)?.await()
            result?.items?.let {
                Result.Success(it)
            } ?: run {
                { Result.Error("empty response") }
            }
        } catch (e: Throwable) {
            e.message?.let { Result.Error(it) }
        }
}