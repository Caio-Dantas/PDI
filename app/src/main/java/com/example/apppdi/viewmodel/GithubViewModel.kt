package com.example.apppdi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.client.GithubAuthorizationClient
import com.example.apppdi.model.AccessToken

class GithubViewModel : ViewModel() {

    val accessTokenLiveData : MutableLiveData<AccessToken> = MutableLiveData()

    fun requestAccessToken(code : String) : LiveData<AccessToken>{
        val githubClient = GithubAuthorizationClient()
        githubClient.getAccessToken(code, object : GithubAuthorizationClient.GithubClientCallback{
            override fun success(accessToken: AccessToken) {
                accessTokenLiveData.postValue(accessToken)
            }
            override fun error() {
                TODO("Not yet implemented")
            }

        })
        return accessTokenLiveData
    }



}