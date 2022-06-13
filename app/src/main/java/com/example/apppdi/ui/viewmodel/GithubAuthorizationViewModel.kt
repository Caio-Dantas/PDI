package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.repository.IGithubAuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.apppdi.repository.IGithubAuthorizationRepository.GithubAuthorizationRepositoryParams


@HiltViewModel
class GithubAuthorizationViewModel @Inject constructor (
) : ViewModel() {

    @Inject
    lateinit var repository : IGithubAuthorizationRepository

    private val accessTokenLiveData: MutableLiveData<AccessToken> = MutableLiveData()

    fun requestAccessToken(code: String){
        repository(GithubAuthorizationRepositoryParams(code) { accessToken ->
            accessTokenLiveData.postValue(accessToken)
        })
    }

    fun getAccessTokenLiveData(): LiveData<AccessToken> {
        return accessTokenLiveData
    }

}