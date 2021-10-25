package com.example.apppdi.model

import java.io.Serializable

data class Repo(
    val name: String,
    val private: Boolean,
    val description: String?,
    val language: String,
    val full_name: String,
    var collaborators_images: List<Image>? = emptyList()
) : Serializable
