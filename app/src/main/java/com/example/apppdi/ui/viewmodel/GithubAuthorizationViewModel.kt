package com.example.apppdi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.client.GithubAuthorizationClient
import com.example.apppdi.model.AccessToken
import com.example.apppdi.repository.GithubAuthorizationRepository

class GithubAuthorizationViewModel(
    private val repository : GithubAuthorizationRepository
) : ViewModel() {


    fun requestAccessToken(code : String): LiveData<AccessToken>{
        return repository.getAccessToken(code)
    }

    fun getAccessToken() : AccessToken?{
        return repository.accessTokenLiveData.value
    }
}