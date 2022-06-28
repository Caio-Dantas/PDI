package com.example.apppdi.di

import com.example.apppdi.network.GitHubApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.apppdi.network.GitHubAuthApi
import com.example.apppdi.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitAuth(): GitHubAuthApi = Retrofit.Builder()
        .baseUrl("https://github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubAuthApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): GitHubApi = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)

    @Provides
    @Singleton
    fun provideAccessTokenRepo() : AccessTokenRepository {
        return AccessTokenRepository()
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindGithubAuthorizationRepository(repository: GithubAuthorizationRepositoryImpl) : GithubAuthorizationRepository

    @Binds
    fun bindGithubRepoRepository(repository: GithubRepoRepositoryImpl) : GithubRepoRepository

}