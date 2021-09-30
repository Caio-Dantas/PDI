package com.example.apppdi.client;

import com.example.apppdi.ENV
import com.example.apppdi.builder.GithubApiReposServiceBuilder
import com.example.apppdi.builder.GithubAuthorizationServiceBuilder
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepoClient {


    fun getRepoList(visibility : String, callback: GithubApiCallback){

        val githubApiReposService = GithubApiReposServiceBuilder.buildRepoService()

        val repoCall = githubApiReposService.getRepos(
            visibility
        )

        repoCall.enqueue(object : Callback<Repo>{
            override fun onFailure(call: Call<Repo>, t: Throwable) {
                callback.error()
            }

            override fun onResponse(call: Call<Repo>, response: Response<Repo>) {
                val repo = response.body() ?: return
                callback.success(repo)
            }
        } )

    }


    interface GithubApiCallback{
        fun success(repo: Repo)
        fun error()
    }


}



