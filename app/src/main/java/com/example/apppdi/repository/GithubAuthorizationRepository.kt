package com.example.apppdi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppdi.ENV
import com.example.apppdi.builder.GithubAuthorizationServiceBuilder
import com.example.apppdi.model.AccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

object GithubAuthorizationRepository : ViewModel() {

    private val accessTokenLiveData : MutableLiveData<AccessToken> = MutableLiveData()

    private val service = GithubAuthorizationServiceBuilder.retrofit


    fun requestAccessToken(code: String) {
        CoroutineScope(IO).launch {
            val accessTokenResponse = service.validateSession(
                    ENV.CLIENT_ID,
                    ENV.CLIENT_SECRET,
                    code
            ).body()
            accessTokenLiveData.postValue(accessTokenResponse)
        }
    }

    fun getAccessToken() : LiveData<AccessToken> {
        return accessTokenLiveData
    }

}