package com.example.apppdi.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apppdi.model.AccessToken
import com.example.apppdi.repository.GithubRepoRepository
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel

class GithubRepoViewModelFactory(
    private val repository: GithubRepoRepository,
    private val accessToken: AccessToken?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return accessToken?.let { GithubRepoViewModel(repository, it) } as T
    }

}