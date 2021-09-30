package com.example.apppdi.model

import java.io.Serializable

data class Repo(
    val name: String,
    val private: Boolean,
    val description: String,
    val languages_url: String,
    val collaborators_url: String
) : Serializable{}
