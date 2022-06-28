package com.example.apppdi.repository

import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import com.example.apppdi.network.GitHubApi
import com.example.apppdi.network.GitHubAuthApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GithubRepoRepository {
    fun loadRepos(visibility: Visibility, updater: (visibility: Visibility, data: List<Repo>?) -> Unit)
    fun loadImages(repo: Repo, repoUpdater: (Repo) -> Unit): Repo
    fun loadReadme(repo: Repo): Repo
}

class GithubRepoRepositoryImpl @Inject constructor(
    private val accessTokenRepository: AccessTokenRepository,
    private val service: GitHubApi,
    ) : GithubRepoRepository {

    private val accessToken by lazy {
        accessTokenRepository.getToken()
    }

    override fun loadRepos(
        visibility: Visibility,
        updater: (visibility: Visibility, data: List<Repo>?) -> Unit ) {

        CoroutineScope(IO).launch {
            val repos = accessToken?.let {
                service.getRepos(
                    visibility = visibility.getTextAsParam(),
                    authorization = it.getAuthToken(),
                ).body()
            }
            updater(visibility, repos)
        }
    }

    override fun loadImages(repo: Repo, repoUpdater: (Repo) -> Unit) : Repo {
        CoroutineScope(IO).launch {
            val res = accessToken?.let { service.getImages(it.getAuthToken(), repo.full_name).body() }
            if (res != null) {
                repo.collaborators_images = res
                repoUpdater(repo)
            }
        }
        return repo
    }

    override fun loadReadme(repo: Repo) : Repo {
        CoroutineScope(IO).launch {
            val res = accessToken?.let { service.getReadme(it.getAuthToken(), repo.full_name).body() }
            repo.html_url_readme = res
        }
        return repo
    }
}