package com.example.apppdi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppdi.R
import com.example.apppdi.repository.GithubPrivateRepoRepository
import com.example.apppdi.repository.GithubPublicRepoRepository
import com.example.apppdi.ui.adapters.CustomAdapter
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import com.example.apppdi.ui.viewmodel.factory.GithubRepoViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPrivateRepos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPrivateRepos : Fragment() {

    private val githubReposViewModel by lazy {
        val factory = GithubRepoViewModelFactory(GithubPublicRepoRepository, GithubPrivateRepoRepository)
        val provider = ViewModelProvider(this, factory)
        provider.get(GithubRepoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listPrivate = view.findViewById<RecyclerView>(R.id.listRepos)
        val layoutManager = GridLayoutManager(activity, 2)
        listPrivate.layoutManager = layoutManager

        githubReposViewModel.privateReposLiveData.observe(viewLifecycleOwner, { repoList ->

            val customAdapter = CustomAdapter(repoList, activity!!)
            listPrivate.adapter = customAdapter

        })

    }
}