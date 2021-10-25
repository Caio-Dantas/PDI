package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import com.example.apppdi.repository.GithubPrivateRepoRepository
import com.example.apppdi.repository.GithubPublicRepoRepository

class GithubRepoViewModel (
    private val repositoryPublic : GithubPublicRepoRepository,
    private val repositoryPrivate : GithubPrivateRepoRepository,
) : ViewModel() {

    val publicReposLiveData : MutableLiveData<List<Repo>> = repositoryPublic.reposLiveData
    val privateReposLiveData : MutableLiveData<List<Repo>> = repositoryPrivate.reposLiveData

    fun loadRepos(accessToken: AccessToken?) {
        if (accessToken != null) {
            repositoryPublic.loadRepos(accessToken)
            repositoryPrivate.loadRepos(accessToken)
        }
    }

}