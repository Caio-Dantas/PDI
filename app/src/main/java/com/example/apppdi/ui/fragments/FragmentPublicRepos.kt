package com.example.apppdi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.apppdi.R
import com.example.apppdi.model.AccessToken
import com.example.apppdi.viewmodel.GithubRepoViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPublicRepos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPublicRepos(
    val accessToken: AccessToken
) : Fragment() {


    private val VISIBILITY : String = "public"

    private val githubReposViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(GithubRepoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        githubReposViewModel.getRepoList(VISIBILITY, accessToken)
        return inflater.inflate(R.layout.fragment_public_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listPublic = view.findViewById<ListView>(R.id.listPublic)

        githubReposViewModel.reposLiveData.observe(viewLifecycleOwner, { repoList ->
            if(listPublic == null) Log.i("PRIVATE", "is null")
            val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, repoList.map { repo -> repo.name })
            listPublic.adapter = adapter
        })
    }

}