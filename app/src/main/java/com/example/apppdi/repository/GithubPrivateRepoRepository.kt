package com.example.apppdi.repository

import androidx.lifecycle.MutableLiveData
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo

object GithubPrivateRepoRepository {
    val reposLiveData: MutableLiveData<List<Repo>> = MutableLiveData()
    val visibility = "private"

    fun loadRepos(accessToken: AccessToken) {
        val githubRepoClient = GithubRepoClient()
        githubRepoClient.getRepoList(
            visibility,
            accessToken,
            object : GithubRepoClient.GithubApiCallback {
                override fun success(repoList: List<Repo>) {
                    reposLiveData.postValue(repoList)
                }

                override fun error() {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}