package com.example.apppdi.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.apppdi.R
import com.example.apppdi.ui.adapters.FragmentRepoTabAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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