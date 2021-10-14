package com.example.apppdi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.apppdi.R
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import com.example.apppdi.ui.viewmodel.factory.GithubAuthorizationViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPrivateRepos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPrivateRepos : Fragment() {

    private val VISIBILITY : String = "private"

    private val githubReposViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(GithubRepoViewModel::class.java)
    }

    private val githubAuthViewModel by lazy {
        val factory = GithubAuthorizationViewModelFactory(GithubAuthorizationRepository)
        val provider = ViewModelProvider(this, factory)
        provider.get(GithubAuthorizationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_private_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listPrivate = view.findViewById<ListView>(R.id.listPrivate)

        val accessToken = githubAuthViewModel.getAccessToken()
        if (accessToken != null)
            githubReposViewModel.getRepoList(VISIBILITY, accessToken).observe(viewLifecycleOwner, { repoList ->
                val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, repoList.map { repo -> repo.name })
                listPrivate.adapter = adapter
            })
    }

}