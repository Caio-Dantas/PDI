package com.example.apppdi.di

import com.example.apppdi.builder.GithubApiReposServiceBuilder
import com.example.apppdi.builder.GithubAuthorizationServiceBuilder
import com.example.apppdi.repository.AccessTokenRepository
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.repository.GithubRepoRepository
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    factory {
        GithubAuthorizationServiceBuilder
    }

    factory {
        GithubApiReposServiceBuilder
    }

    single {
        AccessTokenRepository
    }

    single {
        GithubAuthorizationRepository(tokenRepository = get(), serviceBuilder = get())
    }

    single{
        GithubRepoRepository(tokenRepository = get(), serviceBuilder = get())
    }

    viewModel{
        GithubAuthorizationViewModel(repository = get())
    }

    viewModel {
        GithubRepoViewModel(repository = get())
    }
}