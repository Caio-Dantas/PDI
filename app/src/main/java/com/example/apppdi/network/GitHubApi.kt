package com.example.apppdi.network

import retrofit2.http.*
import retrofit2.Response

import com.example.apppdi.model.Repo
import com.example.apppdi.model.Image
import com.example.apppdi.model.UrlRepo
import com.example.apppdi.model.AccessToken

interface GitHubApi {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("user/repos")
    suspend fun getRepos(
        @Query("visibility")visibility : String,
        @Header("Authorization")authorization: String,
        @Query("affiliation")affiliation : String = "owner",
        @Query("accept")accept : String = "application/vnd.github.v3+json"
    ) : Response<List<Repo>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/repos/{fullname}/collaborators")
    suspend fun getImages(
        @Header("Authorization")authorization: String,
        @Path("fullname", encoded = true)fullname: String
    ) : Response<List<Image>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/repos/{fullname}/readme")
    suspend fun getReadme(
        @Header("Authorization")authorization: String,
        @Path("fullname", encoded = true)fullname: String
    ) : Response<UrlRepo>
}