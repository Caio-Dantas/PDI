package com.example.apppdi.builder

import com.example.apppdi.model.AccessToken
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

object GithubAuthorizationServiceBuilder {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubAccessTokenRequest::class.java)
}

interface GithubAccessTokenRequest {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded()
    fun validateSession(
        @Field("client_id")clientId : String,
        @Field("client_secret")clientSecret : String,
        @Field("code")code : String,
    ) : Call<AccessToken>

}