package com.example.apppdi.ui.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppdi.R
import com.example.apppdi.model.Repo
import com.example.apppdi.ui.activities.ActivityDetailsRepo
import java.util.*

class CustomAdapter(private val repos: List<Repo>, private val context: Context ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_list_item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRepo = repos[position]
        holder.bindView(currentRepo)

        val imagesRecyclerView = holder.itemView.findViewById<RecyclerView>(R.id.imageList)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapter = currentRepo.collaborators_images?.let {
            layoutManager.initialPrefetchItemCount = it.size
            ImageAdapter(it, holder.itemView.context)
        }

        imagesRecyclerView.layoutManager = layoutManager
        imagesRecyclerView.adapter = adapter

    }

    override fun getItemCount(): Int {
        return repos.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bindView(repo: Repo) {

            itemView.setOnClickListener(this)

            val name = itemView.findViewById<TextView>(R.id.txtName)
            val desc = itemView.findViewById<TextView>(R.id.txtDesc)
            val languages = itemView.findViewById<TextView>(R.id.txtLanguage)
            val visibility = itemView.findViewById<ImageView>(R.id.imgVisibility)

            name.text = repo.name
            desc.text = repo.description
            languages.text = repo.language

            val visibilityRes = if (repo.private) android.R.drawable.ic_secure else android.R.drawable.ic_partial_secure
            val visibilityColor = if (repo.private) Color.BLACK else Color.LTGRAY
            visibility.setImageResource(visibilityRes)
            visibility.setColorFilter(visibilityColor)

        }

        override fun onClick(v: View) {
            val currentRepo = repos[adapterPosition]

            if(currentRepo.html_url_readme != null){
                val intent = Intent(v.context, ActivityDetailsRepo::class.java)
                intent.putExtra("repo", currentRepo)
                v.context.startActivity(intent)
            }

        }


    }
}