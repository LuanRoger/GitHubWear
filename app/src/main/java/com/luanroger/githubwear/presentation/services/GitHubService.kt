package com.luanroger.githubwear.presentation.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val BASE_URL = "https://api.github.com/"

interface GitHubService {
    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") user: String): String
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object GitHubApi {
    val service: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }
}