package com.example.apppdi.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.apppdi.R
import com.example.apppdi.model.Repo

class CustomAdapter(private val repos: List<Repo>, private val context: Context ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val recycledViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

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
            Log.i("LOAD_IMG", "is not null here ${currentRepo.collaborators_images}")
            layoutManager.initialPrefetchItemCount = it.size
            ImageAdapter(it, holder.itemView.context)
        }

        imagesRecyclerView.layoutManager = layoutManager
        imagesRecyclerView.adapter = adapter

    }

    override fun getItemCount(): Int {
        return repos.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(repo: Repo) {

            val name = itemView.findViewById<TextView>(R.id.txtName)
            val desc = itemView.findViewById<TextView>(R.id.txtDesc)
            val languages = itemView.findViewById<TextView>(R.id.txtLanguage)

            name.text = repo.name
            desc.text = repo.description
            languages.text = repo.language

        }

    }
}