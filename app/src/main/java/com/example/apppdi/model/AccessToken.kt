package com.example.apppdi.model

import java.io.Serializable

data class AccessToken(
    val access_token: String,
    val token_type : String
    ) : Serializable{

        fun getAuthToken() : String{
            return "token $access_token"
        }
}
