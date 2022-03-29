package com.example.apppdi.di

import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.repository.GithubRepoRepository
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single {
        GithubAuthorizationRepository
    }

    single{
        GithubRepoRepository
    }

    viewModel{
        GithubAuthorizationViewModel(repository = get())
    }
    viewModel { params ->
        GithubRepoViewModel(repository = get(), accessToken = params.get())
    }
}