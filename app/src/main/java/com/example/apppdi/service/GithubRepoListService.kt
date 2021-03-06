package com.example.apppdi.service

import com.example.apppdi.builder.GithubApiReposServiceBuilder
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility

class GithubRepoListService(val accessToken: AccessToken) {

    suspend fun loadRepos(visibility: Visibility, callback: GithubRepoClient.GithubApiCallback) {
        val githubRepoClient = GithubRepoClient()
        val githubRepoCall = GithubApiReposServiceBuilder.retrofit.getRepos(
            visibility.getTextAsParam(),
            accessToken.getAuthToken(),
        )
//        githubRepoClient.getRepoList(
//            githubRepoCall,
//            callback
//        )
    }

//    fun loadImages(repo: Repo, callback: GithubRepoClient.GithubApiCallbackImages){
//        val githubRepoClient = GithubRepoClient()
//
//        val githubRepoImageCall = GithubApiReposServiceBuilder.retrofit.getImages(
//            accessToken.getAuthToken(),
//            repo.full_name
//        )
//
//        githubRepoClient.getRepoImages(
//            githubRepoImageCall,
//            callback
//        )
//    }

//    fun loadReadme(repo: Repo, callback: GithubRepoClient.GithubApiCallbackReadme){
//        val githubRepoClient = GithubRepoClient()
//
//        val githubRepoReadmeCall = GithubApiReposServiceBuilder.retrofit.getReadme(
//            accessToken.getAuthToken(),
//            repo.full_name
//        )
//
//        githubRepoClient.getRepoReadme(
//            githubRepoReadmeCall,
//            callback
//        )
//
//    }

}