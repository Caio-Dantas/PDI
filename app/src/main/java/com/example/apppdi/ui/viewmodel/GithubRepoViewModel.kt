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

    fun loadRepos(accessToken: AccessToken) {
        repositoryPublic.loadRepos(accessToken)
        repositoryPrivate.loadRepos(accessToken)
    }

    fun getPublicRepoList(): MutableLiveData<List<Repo>> {
        return repositoryPublic.reposLiveData
    }

    fun getPrivateRepoList(): MutableLiveData<List<Repo>>{
        return repositoryPrivate.reposLiveData
    }

}