package com.example.apppdi.utils

import android.content.Intent
import android.net.Uri
import com.example.apppdi.ENV

class IntentGenerator {

    fun getAuthorizationIntent(callbackUri : String) : Intent {
        val uri = "https://github.com/login/oauth/authorize?client_id=${ENV.CLIENT_ID}&scope=repo&redirect_uri=$callbackUri"
        return Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    }
}