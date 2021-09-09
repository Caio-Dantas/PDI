package com.example.apppdi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.client.GithubClient
import com.example.apppdi.model.AccessToken

class GithubViewModel : ViewModel() {

    val accessTokenLiveData : MutableLiveData<AccessToken> = MutableLiveData()

    fun requestAccessToken(code : String) : LiveData<AccessToken>{
        val githubClient = GithubClient()
        githubClient.getAccessToken(code, object : GithubClient.GithubClientCallback{
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