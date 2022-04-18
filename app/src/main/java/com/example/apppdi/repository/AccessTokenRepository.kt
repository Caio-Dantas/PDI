package com.example.apppdi.repository

import com.example.apppdi.model.AccessToken

object AccessTokenRepository {
    private var accessToken: AccessToken? = null

    fun setToken(accessToken: AccessToken){
        this.accessToken = accessToken
    }

    fun getToken() : AccessToken? {
        return accessToken
    }
}