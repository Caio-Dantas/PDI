package com.example.apppdi.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apppdi.ENV
import com.example.apppdi.R
import com.example.apppdi.repository.GithubAuthorizationRepository
import com.example.apppdi.utils.IntentGenerator
import com.example.apppdi.ui.viewmodel.GithubAuthorizationViewModel
import com.example.apppdi.ui.viewmodel.factory.GithubAuthorizationViewModelFactory

class ActivityHome : AppCompatActivity() {

    private val githubAuthViewModel by lazy {
        val factory = GithubAuthorizationViewModelFactory(GithubAuthorizationRepository)
        val provider = ViewModelProvider(this, factory)
        provider.get(GithubAuthorizationViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            loginWithGithub()
        }

    }

    override fun onResume() {
        super.onResume()
        val data = intent.data
        if(data != null && data.toString().startsWith(ENV.CALLBACK_URI)){
            val code = data.getQueryParameter("code").toString()
            validateWithGithub(code)
        }
        intent.data = null
    }

    private fun loginWithGithub(){
        val intentGenerator = IntentGenerator()
        val intent = intentGenerator.getAuthorizationIntent(ENV.CALLBACK_URI)
        startActivity(intent)
    }

    private fun validateWithGithub(code : String){
        githubAuthViewModel.requestAccessToken(code)
        githubAuthViewModel.getAccessToken().observe(this, Observer { accessToken ->
            if(accessToken?.access_token?.isNotBlank() != null) {
                val intent = Intent(this, ActivityListHandler::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
    }

}
