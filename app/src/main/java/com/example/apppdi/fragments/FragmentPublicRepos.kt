package com.example.apppdi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apppdi.R


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPublicRepos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPublicRepos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_public_repos, container, false)
    }

}