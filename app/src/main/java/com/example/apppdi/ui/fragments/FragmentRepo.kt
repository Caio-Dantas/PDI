package com.example.apppdi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppdi.R
import com.example.apppdi.model.Repo
import com.example.apppdi.model.Visibility
import com.example.apppdi.ui.adapters.CustomAdapter
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

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

    private val githubReposViewModel: GithubRepoViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        githubReposViewModel.loadRepos()

        val listRepos = view.findViewById<RecyclerView>(R.id.listRepos)
        val layoutManager = GridLayoutManager(activity, 2)
        listRepos.layoutManager = layoutManager

        val dataListAdapter : MutableList<Repo> = mutableListOf()
        val adapter = CustomAdapter(dataListAdapter, requireActivity())
        listRepos.adapter = adapter

        githubReposViewModel
            .getLiveData(arguments?.getSerializable(ARG_VISIBILITY) as Visibility)
            .observe(viewLifecycleOwner, { repoList ->
                if(!repoList.isNullOrEmpty()){
                    dataListAdapter.clear()
                    dataListAdapter.addAll(repoList)
                    adapter.notifyItemRangeChanged(0, repoList.size)

                    githubReposViewModel.getModifiedRepo().observe(viewLifecycleOwner, { modifiedRepo ->
                        adapter.notifyImageChanged(modifiedRepo)
                    })
                }
            })



    }
}