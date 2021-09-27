package com.example.apppdi.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.apppdi.R
import com.example.apppdi.fragments.FragmentPrivateRepos
import com.example.apppdi.fragments.FragmentPublicRepos

class ActivityListHandler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_handler)

        Log.i("LIST_HANDLER", "ACTIVITY_CREATED")

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

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIST_HANDLER", "Destroying")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIST_HANDLER", "Stoping")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.i("LIST_HANDLER", "Back pressed")
    }


    private fun setupRepo(fragmentRepo : Fragment){

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frgHandler, fragmentRepo)
            commit()
        }

    }
}