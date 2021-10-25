package com.example.apppdi.service

import android.util.Log
import com.example.apppdi.builder.GithubApiReposServiceBuilder
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Image
import com.example.apppdi.model.Repo
import com.example.apppdi.repository.GithubPrivateRepoRepository
import com.example.apppdi.repository.GithubPublicRepoRepository

class GithubRepoListService(val accessToken: AccessToken) {

    fun loadRepos(visibility: String, callback: GithubRepoClient.GithubApiCallback) {
        val githubRepoClient = GithubRepoClient()
        val githubRepoCall = GithubApiReposServiceBuilder.retrofit.getRepos(
            visibility,
            accessToken.getAuthToken(),
        )
        githubRepoClient.getRepoList(
            githubRepoCall,
            callback
        )
    }

    fun loadImages(repo: Repo, callback: GithubRepoClient.GithubApiCallbackImages){
        val githubRepoClient = GithubRepoClient()

        val githubRepoImageCall = GithubApiReposServiceBuilder.retrofit.getImages(
            accessToken.getAuthToken(),
            repo.full_name
        )

        githubRepoClient.getRepoImages(
            githubRepoImageCall,
            callback
        )
    }

}