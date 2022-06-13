package com.example.apppdi.repository

import android.util.Log
import com.example.apppdi.ENV
import com.example.apppdi.builder.GithubAuthorizationServiceBuilder
import com.example.apppdi.model.AccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import com.example.apppdi.repository.IGithubAuthorizationRepository.GithubAuthorizationRepositoryParams
import javax.inject.Inject

interface IGithubAuthorizationRepository {
    operator fun invoke(params: GithubAuthorizationRepositoryParams)
    data class GithubAuthorizationRepositoryParams(val code: String, val updater: (accessToken: AccessToken?) -> Unit)
}


class GithubAuthorizationRepository @Inject constructor (
    private val serviceBuilder: GithubAuthorizationServiceBuilder,
    private val tokenRepository: AccessTokenRepository
) : IGithubAuthorizationRepository {

    override fun invoke(params: GithubAuthorizationRepositoryParams) {
        CoroutineScope(IO).launch {
            serviceBuilder.getService().validateSession(
                ENV.CLIENT_ID,
                ENV.CLIENT_SECRET,
                params.code
            ).body().let { accessTokenResponse ->
                if (accessTokenResponse != null) {
                    tokenRepository.setToken(accessTokenResponse)
                }
                params.updater(accessTokenResponse)
            }

        }
        Log.d("CHEGUEI", params.toString())
    }

}