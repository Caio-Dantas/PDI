package com.example.apppdi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo

class GithubRepoViewModel : ViewModel() {

    val reposLiveData : MutableLiveData<List<Repo>> = MutableLiveData()

    fun getRepoList(visibility : String, accessToken: AccessToken) : LiveData<List<Repo>>{
        val githubRepoClient = GithubRepoClient()
        githubRepoClient.getRepoList(visibility, accessToken, object : GithubRepoClient.GithubApiCallback{
            override fun success(repoList: List<Repo>) {
                reposLiveData.postValue(repoList)
            }

            override fun error() {
                TODO("Not yet implemented")
            }

        })
        return reposLiveData
    }



}