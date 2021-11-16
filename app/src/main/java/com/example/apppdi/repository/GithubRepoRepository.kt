package com.example.apppdi.repository

import androidx.lifecycle.MutableLiveData
import com.example.apppdi.client.GithubRepoClient
import com.example.apppdi.model.*
import com.example.apppdi.service.GithubRepoListService

object GithubRepoRepository {


    val publicReposLiveData : MutableLiveData<List<Repo>> = MutableLiveData()
    val privateReposLiveData : MutableLiveData<List<Repo>> = MutableLiveData()

    fun loadRepos(accessToken: AccessToken, visibility: Visibility){
        GithubRepoListService(accessToken).loadRepos(
            visibility,
            object : GithubRepoClient.GithubApiCallback {
                override fun success(repoList: List<Repo>) {
                    repoList.forEach {
                        loadImages(it, accessToken, visibility)
                        loadReadme(it, accessToken, visibility)
                    }

                    when(visibility){
                        Visibility.PUBLIC -> publicReposLiveData.value = repoList
                        Visibility.PRIVATE -> privateReposLiveData.value = repoList
                    }

                }

                override fun error() {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    fun loadImages(repo: Repo, accessToken: AccessToken, visibility: Visibility){
        GithubRepoListService(accessToken).loadImages(repo,
            object : GithubRepoClient.GithubApiCallbackImages {
                override fun success(repoList: List<Image>) {
                    repo.collaborators_images = repoList
                    updateLiveData(visibility)
                }

                override fun error() {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    fun loadReadme(repo: Repo, accessToken: AccessToken, visibility: Visibility){
        GithubRepoListService(accessToken).loadReadme(repo,
            object : GithubRepoClient.GithubApiCallbackReadme {
                override fun success(urlRepo: UrlRepo) {
                    repo.html_url_readme = urlRepo
                    updateLiveData(visibility)
                }

                override fun error() {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    private fun updateLiveData(visibility: Visibility){
        when(visibility){
            Visibility.PUBLIC -> publicReposLiveData.postValue(publicReposLiveData.value)
            Visibility.PRIVATE -> privateReposLiveData.postValue(privateReposLiveData.value)
        }
    }

}