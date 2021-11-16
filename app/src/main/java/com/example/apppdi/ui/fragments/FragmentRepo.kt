package com.example.apppdi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppdi.R
import com.example.apppdi.model.Repo
import com.example.apppdi.ui.adapters.CustomAdapter
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import com.example.apppdi.ui.viewmodel.factory.GithubRepoViewModelFactory
import com.example.apppdi.model.Visibility
import com.example.apppdi.repository.GithubRepoRepository

class FragmentRepo : Fragment() {


    companion object {

        private const val ARG_VISIBILITY : String = "VISIBILITY"

        fun newInstance(visibility: Visibility): FragmentRepo {
            val args = Bundle()
            args.putSerializable(ARG_VISIBILITY, visibility)
            val fragment = FragmentRepo()
            fragment.arguments = args
            return fragment
        }
    }

    private val githubReposViewModel by lazy {
        val factory = GithubRepoViewModelFactory(GithubRepoRepository)
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

        val listRepos = view.findViewById<RecyclerView>(R.id.listRepos)
        val layoutManager = GridLayoutManager(activity, 2)
        listRepos.layoutManager = layoutManager
        val dataListAdapter : MutableList<Repo> = mutableListOf()
        val adapter = CustomAdapter(dataListAdapter, activity!!)
        listRepos.adapter = adapter

        githubReposViewModel
            .getLiveData(arguments?.getSerializable(ARG_VISIBILITY) as Visibility)
            .observe(viewLifecycleOwner, { repoList ->
                dataListAdapter.clear()
                dataListAdapter.addAll(repoList)
                adapter.notifyDataSetChanged()
        })

    }
}