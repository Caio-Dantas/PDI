package com.example.apppdi.repository

import android.util.Log
import com.example.apppdi.ENV
import javax.inject.Inject

import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.CoroutineScope

import com.example.apppdi.model.AccessToken
import com.example.apppdi.network.GitHubApi
import com.example.apppdi.repository.GithubAuthorizationRepository.Params

interface GithubAuthorizationRepository {
    operator fun invoke(params: Params)

    data class Params(
        val code: String,
        val updater: (accessToken: AccessToken?) -> Unit
    )
}

class GithubAuthorizationRepositoryImpl @Inject constructor (
    private val service: GitHubApi,
) : GithubAuthorizationRepository {
    override fun invoke(params: Params) {
        CoroutineScope(IO).launch {
            service.validateSession(
                ENV.CLIENT_ID,
                ENV.CLIENT_SECRET,
                params.code
            ).body().let { accessTokenResponse ->
                if (accessTokenResponse != null) Log.d("CAFÉ", accessTokenResponse.getAuthToken())

                params.updater(accessTokenResponse)
            }
        }
    }
}