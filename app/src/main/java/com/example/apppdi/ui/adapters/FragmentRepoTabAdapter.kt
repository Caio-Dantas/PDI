package com.example.apppdi.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.apppdi.ui.fragments.FragmentRepo
import com.example.apppdi.model.Visibility

class FragmentRepoTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            1 -> (return FragmentRepo(Visibility.PRIVATE))
            else -> (return FragmentRepo(Visibility.PUBLIC))
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            1 -> (return Visibility.PRIVATE.getText())
            else -> (return Visibility.PUBLIC.getText())
        }
    }

}