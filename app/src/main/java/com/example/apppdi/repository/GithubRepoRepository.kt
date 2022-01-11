package com.example.apppdi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apppdi.builder.GithubApiReposServiceBuilder
import com.example.apppdi.model.AccessToken
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

object GithubRepoRepository{

    private val service = GithubApiReposServiceBuilder.retrofit

    fun loadRepos(accessToken: AccessToken, visibility: Visibility, updater: (visibility: Visibility, data: List<Repo>?) -> Unit) : LiveData<List<Repo>> {
        val liveData = MutableLiveData<List<Repo>>()

        CoroutineScope(IO).launch {
            val repos = service.getRepos(visibility = visibility.getTextAsParam(),
                authorization = accessToken.getAuthToken(),
            ).body()

            repos?.map {
                loadImages(it, accessToken)
                loadReadme(it, accessToken)
            }
            updater(visibility, repos)
        }
        return liveData

    }

    fun loadImages(repo: Repo, accessToken: AccessToken) : Repo{
        CoroutineScope(IO).launch {
            val res = service.getImages(accessToken.getAuthToken(), repo.full_name).body()
            if (res != null) {
                repo.collaborators_images = res
            }
        }
        return repo
    }

    fun loadReadme(repo: Repo, accessToken: AccessToken) : Repo{
        CoroutineScope(IO).launch {
            val res = service.getReadme(accessToken.getAuthToken(), repo.full_name).body()
            repo.html_url_readme = res
        }
        return repo
    }


}