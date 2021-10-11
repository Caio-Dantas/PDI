package com.example.apppdi.client;

import com.example.apppdi.builder.GithubApiReposServiceBuilder
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepoClient {


    fun getRepoList(visibility : String, accessToken: AccessToken, callback: GithubApiCallback){

        val githubApiReposService = GithubApiReposServiceBuilder.retrofit

        val repoCall = githubApiReposService.getRepos(
            visibility,
            accessToken.getAuthToken()
        )

        repoCall.enqueue(object : Callback<List<Repo>>{
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                callback.error()
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                val repo = response.body() ?: return
                callback.success(repo)
            }
        } )

    }


    interface GithubApiCallback{
        fun success(repoList: List<Repo>)
        fun error()
    }


}



