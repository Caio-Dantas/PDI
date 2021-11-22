package com.example.apppdi.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apppdi.R
import com.example.apppdi.model.Repo

class ActivityDetailsRepo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_repo)

        val repo: Repo = intent.getSerializableExtra("repo") as Repo

        val webviewReadme = findViewById<WebView>(R.id.wbvReadme)
        val txtFullname = findViewById<TextView>(R.id.txtFullname)
        val txtDescDetail = findViewById<TextView>(R.id.txtDescDetail)
        val txtStars = findViewById<TextView>(R.id.txtStars)
        val txtForks = findViewById<TextView>(R.id.txtForks)
        val image = findViewById<ImageView>(R.id.imgVisibilityDesc)

        webviewReadme.loadUrl(repo.html_url_readme.toString())
        txtFullname.text = repo.full_name
        txtDescDetail.text = repo.description
        txtStars.text = repo.watchers_count.toString()
        txtForks.text = repo.forks_count.toString()

        val visibilityRes = if (repo.private) android.R.drawable.ic_secure else android.R.drawable.ic_partial_secure
        val visibilityColor = if (repo.private) Color.BLACK else Color.LTGRAY
        image.setImageResource(visibilityRes)
        image.setColorFilter(visibilityColor)

    }
}