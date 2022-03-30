package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.repository.GithubAuthorizationRepository

class GithubAuthorizationViewModel(
    private val repository : GithubAuthorizationRepository
) : ViewModel() {

    private val accessTokenLiveData: MutableLiveData<AccessToken> = MutableLiveData()

    fun requestAccessToken(code: String){
        repository.requestAccessToken(code) { accessToken ->
            accessTokenLiveData.postValue(accessToken)
        }
    }

    fun getAccessTokenLiveData(): LiveData<AccessToken> {
        return accessTokenLiveData
    }

}