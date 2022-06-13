package com.example.apppdi.di

import com.example.apppdi.builder.GithubAuthorizationServiceBuilder
import com.example.apppdi.repository.AccessTokenRepository
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.repository.IGithubAuthorizationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Provides
    fun provideAccessTokenRepo() : AccessTokenRepository {
        return AccessTokenRepository()
    }

    @Singleton
    @Provides
    fun provideGithubAuthServiceBuilder() : GithubAuthorizationServiceBuilder {
        return GithubAuthorizationServiceBuilder()
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindGithubAuthorizationRepository(repository: GithubAuthorizationRepository) : IGithubAuthorizationRepository
}