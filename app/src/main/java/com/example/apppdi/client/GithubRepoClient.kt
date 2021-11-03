package com.example.apppdi.client;

import com.example.apppdi.model.Image
import com.example.apppdi.model.Repo
import com.example.apppdi.model.UrlRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepoClient {


    fun getRepoList(repoCall : Call<List<Repo>>, callback: GithubApiCallback){

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

    fun getRepoImages(repoCall: Call<List<Image>>, callback: GithubApiCallbackImages){
        repoCall.enqueue(object : Callback<List<Image>>{
            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                callback.error()
            }

            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                val repo = response.body() ?: return
                callback.success(repo)
            }
        } )
    }

    fun getRepoReadme(repoCall: Call<UrlRepo>, callback: GithubApiCallbackReadme){
        repoCall.enqueue(object : Callback<UrlRepo>{
            override fun onFailure(call: Call<UrlRepo>, t: Throwable) {
                callback.error()
            }

            override fun onResponse(call: Call<UrlRepo>, response: Response<UrlRepo>) {
                val repo = response.body() ?: return
                callback.success(repo)
            }
        } )
    }


    interface GithubApiCallback{
        fun success(repoList: List<Repo>)
        fun error()
    }

    interface GithubApiCallbackImages{
        fun success(repoList: List<Image>)
        fun error()
    }

    interface GithubApiCallbackReadme {
        fun success(url: UrlRepo)
        fun error()
    }


}



