package com.example.apppdi.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.apppdi.network.GitHubApi
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.repository.GithubAuthorizationRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): GitHubApi = Retrofit.Builder()
        .baseUrl("https://github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindGithubAuthorizationRepository(repository: GithubAuthorizationRepositoryImpl) : GithubAuthorizationRepository
}