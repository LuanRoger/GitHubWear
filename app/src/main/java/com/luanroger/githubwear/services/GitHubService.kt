package com.luanroger.githubwear.services

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private val BASE_URL = "https://api.github.com/"

data class User(
    val id: String,

    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("public_repos")
    val publicRepos: Int,

    @SerializedName("public_gists")
    val publicGists: Int,

    val followers: Int,

    val following: Int,

    val location: String?,

    val company: String?,

    val hireable: Boolean?,

    val blog: String?,

    val bio: String?,
)

interface GitHubService {
    @Headers("User-Agent: GitHub Wear")
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