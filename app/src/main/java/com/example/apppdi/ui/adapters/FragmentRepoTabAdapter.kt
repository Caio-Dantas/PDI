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
            1 -> (return FragmentRepo.newInstance(Visibility.PRIVATE))
            else -> (return FragmentRepo.newInstance(Visibility.PRIVATE))
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            1 -> (return Visibility.PRIVATE.getDisplayText())
            else -> (return Visibility.PUBLIC.getDisplayText())
        }
    }

}