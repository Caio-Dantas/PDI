package com.example.apppdi.model

import java.io.Serializable

data class UrlRepo(
    val download_url: String
) : Serializable{
    override fun toString(): String {
        return download_url
    }
}