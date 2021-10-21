package com.example.apppdi.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.apppdi.R
import com.example.apppdi.model.AccessToken
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.repository.GithubPrivateRepoRepository
import com.example.apppdi.repository.GithubPublicRepoRepository
import com.example.apppdi.ui.fragments.FragmentPrivateRepos
import com.example.apppdi.ui.fragments.FragmentPublicRepos
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import com.example.apppdi.ui.viewmodel.factory.GithubAuthorizationViewModelFactory
import com.example.apppdi.ui.viewmodel.factory.GithubRepoViewModelFactory
import com.example.apppdi.utils.RepoTabAdapter
import com.google.android.material.tabs.TabLayout

class ActivityListHandler : AppCompatActivity() {

    private val githubReposViewModel by lazy {
        val factory = GithubRepoViewModelFactory(GithubPublicRepoRepository, GithubPrivateRepoRepository)
        val provider = ViewModelProvider(this, factory)
        provider.get(GithubRepoViewModel::class.java)
    }

    private val githubAuthViewModel by lazy {
        val factory = GithubAuthorizationViewModelFactory(GithubAuthorizationRepository)
        val provider = ViewModelProvider(this, factory)
        provider.get(GithubAuthorizationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_handler)

        val accessToken = githubAuthViewModel.getAccessToken()

        if (accessToken != null) {
            githubReposViewModel.loadRepos(accessToken)
        }

        val viewPager = findViewById<ViewPager>(R.id.vpgHolderRepo)
        val tabLayout = findViewById<TabLayout>(R.id.tblLayoutHandler)

        viewPager.adapter = RepoTabAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

    }

}