package com.example.apppdi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apppdi.builder.GithubApiReposServiceBuilder
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Image
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import com.example.apppdi.service.GithubRepoListService

object GithubPrivateRepoRepository {
//    val reposLiveData: MutableLiveData<List<Repo>> = MutableLiveData()
    var reposLiveData: MutableLiveData<List<Repo>> = MutableLiveData()
    val visibility = "private"


    fun loadRepos(accessToken: AccessToken){
        GithubRepoListService(accessToken).loadRepos(Visibility.PRIVATE,
            object : GithubRepoClient.GithubApiCallback {
                override fun success(repoList: List<Repo>) {
                    repoList.forEach {
                        loadImages(it, accessToken)
                    }

                    reposLiveData.value = repoList
                }

                override fun error() {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    fun loadImages(repo: Repo, accessToken: AccessToken){
        GithubRepoListService(accessToken).loadImages(repo,
            object : GithubRepoClient.GithubApiCallbackImages {
                override fun success(repoList: List<Image>) {
                    repo.collaborators_images = repoList
                    reposLiveData.postValue(reposLiveData.value)
                }

                override fun error() {
                    TODO("Not yet implemented")
                }

            }
        )
    }

}