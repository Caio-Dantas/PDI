package com.example.apppdi.model

import java.io.Serializable

data class Repo(
    val name: String,
    val private: Boolean,
    val description: String?,
    val language: String,
    val full_name: String,
    val forks_count: Int,
    val watchers_count: Int,
    var collaborators_images: List<Image>,
    var html_url_readme: UrlRepo? = null
) : Serializable
