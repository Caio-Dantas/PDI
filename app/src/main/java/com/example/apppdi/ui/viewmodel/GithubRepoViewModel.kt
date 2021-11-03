package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import com.example.apppdi.repository.GithubPrivateRepoRepository
import com.example.apppdi.repository.GithubPublicRepoRepository
import com.example.apppdi.model.Visibility

class GithubRepoViewModel (
    private val repositoryPublic : GithubPublicRepoRepository,
    private val repositoryPrivate : GithubPrivateRepoRepository,
) : ViewModel() {

    private val publicReposLiveData : MutableLiveData<List<Repo>> = repositoryPublic.reposLiveData
    private val privateReposLiveData : MutableLiveData<List<Repo>> = repositoryPrivate.reposLiveData

    fun loadRepos(accessToken: AccessToken?) {
        if (accessToken != null) {
            repositoryPublic.loadRepos(accessToken)
            repositoryPrivate.loadRepos(accessToken)
        }
    }

    fun getLiveData(visibility: Visibility) : MutableLiveData<List<Repo>> {
        return when(visibility) {
            Visibility.PRIVATE -> privateReposLiveData
            else -> publicReposLiveData
        }
    }

}