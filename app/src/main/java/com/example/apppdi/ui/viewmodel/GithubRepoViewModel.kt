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

    fun loadRepos(accessToken: AccessToken?) {
        if (accessToken != null) {
            repository.loadRepos(accessToken, Visibility.PRIVATE)
            repository.loadRepos(accessToken, Visibility.PUBLIC)
        }
    }

    fun getLiveData(visibility: Visibility) : MutableLiveData<List<Repo>> {
        Log.i("REFACTOR", "getting livedata ${visibility.getDisplayText()}")
        return when(visibility) {
            Visibility.PRIVATE -> repository.privateReposLiveData
            else -> repository.publicReposLiveData
        }
    }

}