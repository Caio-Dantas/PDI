package com.example.apppdi.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apppdi.ENV
import com.example.apppdi.R
import com.example.apppdi.utils.IntentGenerator
import com.example.apppdi.viewmodel.GithubViewModel

class ActivityHome : AppCompatActivity() {

    private val githubViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(GithubViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener(View.OnClickListener {
            loginWithGithub()
        })

    }

    override fun onResume() {
        super.onResume()

        val data = intent.data
        if(data != null && data.toString().startsWith(ENV.CALLBACK_URI)){
            val code = data.getQueryParameter("code").toString()
            validateWithGithub(code)
        }
    }

    private fun loginWithGithub(){
        val intentGenerator = IntentGenerator()
        val intent = intentGenerator.getAuthorizationIntent(ENV.CALLBACK_URI)
        startActivity(intent)
    }

    private fun validateWithGithub(code : String){
        githubViewModel.requestAccessToken(code).observe(this, Observer { accessToken ->
            Log.i("TOKEN", accessToken.toString())
            if(!accessToken.access_token.isNullOrBlank()) Toast.makeText(this, accessToken.access_token, Toast.LENGTH_SHORT).show()
        })
    }
}
