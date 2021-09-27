package com.example.apppdi.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apppdi.R
import com.example.apppdi.fragments.FragmentPrivateRepos
import com.example.apppdi.fragments.FragmentPublicRepos

class ActivityListHandler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_handler)

        val statusBarPublic = findViewById<View>(R.id.vwPublic)
        val statusBarPrivate = findViewById<View>(R.id.vwPrivate)

        val fragmentPublicRepos = FragmentPublicRepos()
        val fragmentPrivateRepos = FragmentPrivateRepos()

        setupRepo(fragmentPublicRepos)

        val btnPublicRepos = findViewById<Button>(R.id.btnPublic)
        btnPublicRepos.setOnClickListener {
            setupRepo(fragmentPublicRepos)
            statusBarPrivate.visibility = View.INVISIBLE
            statusBarPublic.visibility = View.VISIBLE
        }

        val btnPrivateRepos = findViewById<Button>(R.id.btnPrivate)
        btnPrivateRepos.setOnClickListener {
            setupRepo(fragmentPrivateRepos)
            statusBarPublic.visibility = View.INVISIBLE
            statusBarPrivate.visibility = View.VISIBLE
        }
    }


    private fun setupRepo(fragmentRepo : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frgHandler, fragmentRepo)
            commit()
        }
    }
}