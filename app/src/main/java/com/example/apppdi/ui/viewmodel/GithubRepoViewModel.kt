package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import com.example.apppdi.repository.GithubRepoRepository

class GithubRepoViewModel (
    private val repository : GithubRepoRepository,
    private val accessToken: AccessToken
) : ViewModel() {

    private val publicReposLiveData by lazy {
        repository.loadRepos(accessToken, Visibility.PUBLIC)
    }
    private val privateReposLiveData by lazy {
        repository.loadRepos(accessToken, Visibility.PRIVATE)
    }


    fun getLiveData(visibility: Visibility, accessToken: AccessToken) : LiveData<List<Repo>> {
        return when(visibility) {
            Visibility.PRIVATE -> privateReposLiveData
            else -> publicReposLiveData
        }
    }

}