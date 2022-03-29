package com.example.apppdi.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.apppdi.R
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.repository.GithubRepoRepository
import com.example.apppdi.ui.adapters.FragmentRepoTabAdapter
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel
import com.example.apppdi.ui.viewmodel.GithubRepoViewModel
import com.example.apppdi.ui.viewmodel.factory.GithubAuthorizationViewModelFactory
import com.example.apppdi.ui.viewmodel.factory.GithubRepoViewModelFactory
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class ActivityListHandler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_handler)

        val viewPager = findViewById<ViewPager>(R.id.vpgHolderRepo)
        val tabLayout = findViewById<TabLayout>(R.id.tblLayoutHandler)

        viewPager.adapter = FragmentRepoTabAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

    }

}