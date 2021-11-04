package com.example.apppdi.repository

import androidx.lifecycle.MutableLiveData
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.*
import com.example.apppdi.service.GithubRepoListService

object GithubRepoRepository {

    val reposLiveData : MutableLiveData<List<Repo>> = MutableLiveData()

    fun loadRepos(accessToken: AccessToken, visibility: Visibility, callback: LoadRepoCallback){
        GithubRepoListService(accessToken).loadRepos(
            visibility,
            object : GithubRepoClient.GithubApiCallback {
                override fun success(repoList: List<Repo>) {
                    repoList.forEach {
                        loadImages(it, accessToken)
                        loadReadme(it, accessToken)
                    }

                    reposLiveData.value = repoList
                    callback.success(reposLiveData)

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

    fun loadReadme(repo: Repo, accessToken: AccessToken){
        GithubRepoListService(accessToken).loadReadme(repo,
            object : GithubRepoClient.GithubApiCallbackReadme {
                override fun success(urlRepo: UrlRepo) {
                    repo.html_url_readme = urlRepo
                    reposLiveData.postValue(reposLiveData.value)
                }

                override fun error() {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    interface LoadRepoCallback{
        fun success(repoList: MutableLiveData<List<Repo>>)
        fun error()
    }

}