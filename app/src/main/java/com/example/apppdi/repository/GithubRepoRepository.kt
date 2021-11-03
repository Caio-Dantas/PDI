package com.example.apppdi.repository

import androidx.lifecycle.MutableLiveData
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Image
import com.example.apppdi.model.Repo
import com.example.apppdi.model.UrlRepo
import com.example.apppdi.service.GithubRepoListService

object GithubRepoRepository {
    val reposLiveData : MutableLiveData<List<Repo>> = MutableLiveData()
    val visibility = "public"

    fun loadRepos(accessToken: AccessToken){
        GithubRepoListService(accessToken).loadRepos(
            visibility,
            object : GithubRepoClient.GithubApiCallback {
                override fun success(repoList: List<Repo>) {
                    repoList.forEach {
                        loadImages(it, accessToken)
                        loadReadme(it, accessToken)
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
}