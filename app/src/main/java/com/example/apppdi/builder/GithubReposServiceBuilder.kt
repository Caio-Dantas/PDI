package com.example.apppdi.builder

import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object GithubApiReposServiceBuilder {

    val retrofit: GithubRepoRequest = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubRepoRequest::class.java)
}

interface GithubRepoRequest {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("user/repos")
    fun getRepos(
        @Query("visibility")visibility : String,
        @Header("Authorization")authorization: String,
        @Query("affiliation")affiliation : String = "owner",
        @Query("accept")accept : String = "application/vnd.github.v3+json"
    ) : Call<List<Repo>>
}