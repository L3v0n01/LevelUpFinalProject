package com.adobe.finalProject.stories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R
import com.bumptech.glide.Glide

class StoryImagesAdapter(
    private val context: Context,
    private var images: List<StoriesResponseModel.Result.Multimedia>
) : RecyclerView.Adapter<StoryImagesAdapter.StoryImageViewHolder>(){

    class StoryImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.storyImage)

        fun setImageView(image: StoriesResponseModel.Result.Multimedia, context: Context) {

            Glide.with(context)
                .load(image.url)
                .centerCrop()
                .placeholder(R.drawable.ic_newspaper_outline)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryImageViewHolder {

        return StoryImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_story_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoryImageViewHolder, position: Int) {

        holder.setImageView(images[position], context)
    }

    override fun getItemCount(): Int = images.size
}