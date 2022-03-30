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

class GithubRepoRepository(private val tokenRepository: AccessTokenRepository, private val serviceBuilder: GithubApiReposServiceBuilder){

    private val accessToken by lazy {
        tokenRepository.getToken()
    }

    fun loadRepos(
        visibility: Visibility,
        updater: (visibility: Visibility, data: List<Repo>?) -> Unit ) : LiveData<List<Repo>> {

        val liveData = MutableLiveData<List<Repo>>()

        CoroutineScope(IO).launch {
            val repos = accessToken?.let {
                serviceBuilder.getService().getRepos(visibility = visibility.getTextAsParam(),
                    authorization = it.getAuthToken(),
                ).body()
            }

            updater(visibility, repos)
        }
        return liveData

    }

    fun loadImages(repo: Repo, repoUpdater: (Repo) -> Unit) : Repo{
        CoroutineScope(IO).launch {
            val res = accessToken?.let { serviceBuilder.getService().getImages(it.getAuthToken(), repo.full_name).body() }
            if (res != null) {
                repo.collaborators_images = res
                repoUpdater(repo)
            }
        }
        return repo
    }

    fun loadReadme(repo: Repo) : Repo{
        CoroutineScope(IO).launch {
            val res = accessToken?.let { serviceBuilder.getService().getReadme(it.getAuthToken(), repo.full_name).body() }
            repo.html_url_readme = res
        }
        return repo
    }


}