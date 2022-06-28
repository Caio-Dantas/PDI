package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import com.example.apppdi.repository.GithubRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubRepoViewModel @Inject constructor(
    private val repository : GithubRepoRepository
) : ViewModel() {

    private val publicReposLiveData = MutableLiveData<List<Repo>>()
    private val privateReposLiveData = MutableLiveData<List<Repo>>()

    private val modifiedRepo = MutableLiveData<Repo>()

    private fun updateLiveData(visibility: Visibility, data: List<Repo>?){

        data?.let {
            when(visibility) {
                Visibility.PRIVATE -> privateReposLiveData.postValue(it)
                else -> publicReposLiveData.postValue(it)
            }
            it.map { repo ->
                repository.loadReadme(repo)
                repository.loadImages(repo, this::updateModifiedRepo)
            }
        }


    }

    private fun updateModifiedRepo(repo: Repo){
        modifiedRepo.postValue(repo)
    }

    fun getModifiedRepo() : LiveData<Repo>{
        return modifiedRepo
    }

    fun loadRepos(){
        repository.loadRepos(Visibility.PUBLIC, this::updateLiveData)
        repository.loadRepos(Visibility.PRIVATE, this::updateLiveData)
    }

    fun getLiveData(visibility: Visibility) : LiveData<List<Repo>> {
        return when(visibility) {
            Visibility.PRIVATE -> privateReposLiveData
            else -> publicReposLiveData
        }
    }
}