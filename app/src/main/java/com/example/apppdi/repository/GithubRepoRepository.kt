package com.example.apppdi.repository

class GithubRepoRepository {

    /*fun loadRepos(
        visibility: Visibility,
        updater: (visibility: Visibility, data: List<Repo>?) -> Unit ) {

        CoroutineScope(IO).launch {
            val repos = accessToken?.let {
                serviceBuilder.getService().getRepos(
                    visibility = visibility.getTextAsParam(),
                    authorization = it.getAuthToken(),
                ).body()
            }
            updater(visibility, repos)
        }
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
    }*/
}