package com.example.apppdi.client;

import com.example.apppdi.ENV
import com.example.apppdi.builder.GithubAuthorizationServiceBuilder
import com.example.apppdi.model.AccessToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubAuthorizationClient {


    fun getAccessToken(code : String, callback: GithubClientCallback){

        val githubAccessTokenService = GithubAuthorizationServiceBuilder.buildAccessTokenService()

        val accessTokenCall : Call<AccessToken> = githubAccessTokenService.validateSession(
            ENV.CLIENT_ID,
            ENV.CLIENT_SECRET,
            code
        )
        accessTokenCall.enqueue(object : Callback<AccessToken> {
            override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                callback.error()
            }

            override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                val accessToken = response.body() ?: return
                callback.success(accessToken)
            }
        })

    }


    interface GithubClientCallback{
        fun success(accessToken: AccessToken)
        fun error()
    }


}



