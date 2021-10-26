package com.example.apppdi.ui.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.apppdi.R
import com.example.apppdi.model.Image
import com.squareup.picasso.Picasso

class ImageAdapter(private val images: List<Image>, private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentImage = images[position]
        holder.bindView(currentImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(currentImage: Image) {
            val imageView = itemView.findViewById<ImageView>(R.id.imgAvatar)
//            if(currentImage.avatar_url.equals("https://avatars.githubusercontent.com/u/55471180?v=4")) imageView.setBackgroundColor(Color.BLUE)
//            else imageView.setBackgroundColor(Color.RED)

            Picasso.get().load(currentImage.avatar_url).into(object : com.squareup.picasso.Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    // loaded bitmap is here (bitmap)
                    imageView.setImageBitmap(bitmap)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    imageView.setBackgroundColor(Color.RED)
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }
            })

        }

    }
}