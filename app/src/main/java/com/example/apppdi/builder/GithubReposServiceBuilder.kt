package com.example.apppdi.builder

import androidx.lifecycle.LiveData
import com.example.apppdi.model.Image
import com.example.apppdi.model.Repo
import com.example.apppdi.model.UrlRepo
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

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/repos/{fullname}/collaborators")
    fun getImages(
        @Header("Authorization")authorization: String,
        @Path("fullname", encoded = true)fullname: String
    ) : Call<List<Image>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/repos/{fullname}/readme")
    fun getReadme(
        @Header("Authorization")authorization: String,
        @Path("fullname", encoded = true)fullname: String
    ) : Call<UrlRepo>
}