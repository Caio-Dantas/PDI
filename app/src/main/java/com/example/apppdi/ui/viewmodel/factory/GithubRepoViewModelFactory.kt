package com.example.apppdi.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apppdi.repository.GithubPrivateRepoRepository
import com.example.apppdi.repository.GithubPublicRepoRepository
import com.example.apppdi.repository.GithubRepoRepository
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel

class GithubRepoViewModelFactory(
    private val repository: GithubRepoRepository
//    private val publicRepository : GithubPublicRepoRepository,
//    private val privateRepository: GithubPrivateRepoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubRepoViewModel(repository) as T
//        return GithubRepoViewModel(publicRepository, privateRepository) as T
    }

}