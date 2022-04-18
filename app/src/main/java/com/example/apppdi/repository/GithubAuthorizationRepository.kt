package com.example.apppdi.repository

import androidx.lifecycle.ViewModel
import com.example.apppdi.ENV
import com.example.apppdi.builder.GithubAuthorizationServiceBuilder
import com.example.apppdi.model.AccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class GithubAuthorizationRepository(private val tokenRepository: AccessTokenRepository, private val serviceBuilder: GithubAuthorizationServiceBuilder) : ViewModel() {

    fun requestAccessToken(code: String, updater: (accessToken: AccessToken?) -> Unit) {
        CoroutineScope(IO).launch {
            serviceBuilder.getService().validateSession(
                ENV.CLIENT_ID,
                ENV.CLIENT_SECRET,
                code
            ).body().let { accessTokenResponse ->
                if (accessTokenResponse != null) {
                    tokenRepository.setToken(accessTokenResponse)
                }
                updater(accessTokenResponse)
            }

        }
    }

}