package com.example.apppdi.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.apppdi.ui.fragments.FragmentPrivateRepos
import com.example.apppdi.ui.fragments.FragmentPublicRepos

class RepoTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            1 -> (return FragmentPrivateRepos())
            else -> (return FragmentPublicRepos())
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            1 -> (return "Privados")
            else -> (return "Públicos")
        }
    }

}