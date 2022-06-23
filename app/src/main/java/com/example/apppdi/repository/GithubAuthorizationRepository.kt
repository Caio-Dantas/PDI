package com.example.apppdi.repository

import android.util.Log
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
                "105c3f153cec6b39fdaa",
                "652e3e106d968022bfdc5b34f0f9aca46e53c990",
                params.code
            ).body().let { accessTokenResponse ->
                if (accessTokenResponse != null) Log.d("CAFÉ", accessTokenResponse.getAuthToken())

                params.updater(accessTokenResponse)
            }
        }
    }
}