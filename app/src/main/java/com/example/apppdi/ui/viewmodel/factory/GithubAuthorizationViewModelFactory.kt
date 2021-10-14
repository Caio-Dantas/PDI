package com.example.apppdi.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel

class GithubAuthorizationViewModelFactory(
    private val repository : GithubAuthorizationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubAuthorizationViewModel(repository) as T
    }

}