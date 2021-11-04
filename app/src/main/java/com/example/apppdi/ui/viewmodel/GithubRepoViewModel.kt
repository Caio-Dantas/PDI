package com.example.apppdi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import com.example.apppdi.repository.GithubRepoRepository

class GithubRepoViewModel (
    private val repository : GithubRepoRepository
) : ViewModel() {

    private val publicReposLiveData : MutableLiveData<List<Repo>> = MutableLiveData()
    val privateReposLiveData : MutableLiveData<List<Repo>> = MutableLiveData()

    fun loadRepos(accessToken: AccessToken?) {
        if (accessToken != null) {
            repository.loadRepos(accessToken, Visibility.PRIVATE, object: GithubRepoRepository.LoadRepoCallback{
                override fun success(repoList: MutableLiveData<List<Repo>>) {
                    repoList.observeForever {
                        privateReposLiveData.postValue(it)
                    }
                }

                override fun error() {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    fun getLiveData(visibility: Visibility) : MutableLiveData<List<Repo>> {
        return when(visibility) {
            Visibility.PRIVATE -> privateReposLiveData
            else -> publicReposLiveData
        }
    }

}