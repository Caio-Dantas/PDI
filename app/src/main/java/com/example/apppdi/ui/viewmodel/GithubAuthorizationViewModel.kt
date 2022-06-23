package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.model.AccessToken
import com.example.apppdi.repository.GithubAuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubAuthorizationViewModel @Inject constructor (
    private val repository: GithubAuthorizationRepository
) : ViewModel() {

    private val accessTokenLiveData: MutableLiveData<AccessToken> = MutableLiveData()

    fun requestAccessToken(code: String){
        repository(GithubAuthorizationRepository.Params(code) { accessToken ->
            accessTokenLiveData.postValue(accessToken)
        })
    }

    fun getAccessTokenLiveData(): LiveData<AccessToken> {
        return accessTokenLiveData
    }
}