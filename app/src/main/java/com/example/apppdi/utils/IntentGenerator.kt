package com.example.apppdi.utils

import android.content.Intent
import android.net.Uri

class IntentGenerator {
    fun getAuthorizationIntent(callbackUri : String) : Intent {
        val uri = "https://github.com/login/oauth/authorize?client_id=105c3f153cec6b39fdaa&scope=repo&redirect_uri=$callbackUri"
        return Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    }
}