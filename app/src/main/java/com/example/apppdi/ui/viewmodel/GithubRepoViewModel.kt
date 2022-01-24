package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import com.example.apppdi.repository.GithubRepoRepository

class GithubRepoViewModel (
    private val repository : GithubRepoRepository,
    private val accessToken: AccessToken
) : ViewModel() {

    private val publicReposLiveData = MutableLiveData<List<Repo>>()
    private val privateReposLiveData = MutableLiveData<List<Repo>>()

    private val modifiedRepo = MutableLiveData<Repo>()

    private fun updateLiveData(visibility: Visibility, data: List<Repo>?){

        if(data.isNullOrEmpty()) return
        data.map {
            repository.loadReadme(it, accessToken)
            repository.loadImages(it, accessToken, this::updateModifiedRepo)
        }
        when(visibility) {
            Visibility.PRIVATE -> privateReposLiveData.postValue(data)
            else -> publicReposLiveData.postValue(data)
        }
    }

    fun updateModifiedRepo(repo: Repo){
        modifiedRepo.postValue(repo)
    }

    fun getModifiedRepo() : LiveData<Repo>{
        return modifiedRepo
    }

    fun loadRepos(){
        repository.loadRepos(accessToken, Visibility.PUBLIC, this::updateLiveData)
        repository.loadRepos(accessToken, Visibility.PRIVATE, this::updateLiveData)
    }


    fun getLiveData(visibility: Visibility) : LiveData<List<Repo>> {
        return when(visibility) {
            Visibility.PRIVATE -> privateReposLiveData
            else -> publicReposLiveData
        }
    }

}