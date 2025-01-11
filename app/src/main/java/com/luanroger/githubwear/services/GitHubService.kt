package com.luanroger.githubwear.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val BASE_URL = "https://api.github.com/"

data class User(
    val id: String,

    val login: String,

    val avatar_url: String
)

interface GitHubService {
    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") user: String): User
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object GitHubApi {
    val service: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }
}