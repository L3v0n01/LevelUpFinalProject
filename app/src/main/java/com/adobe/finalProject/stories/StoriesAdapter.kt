package com.adobe.finalProject.stories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.adobe.finalProject.R
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StoriesAdapter(
    private var stories: List<StoriesResponseModel.Result>
) : RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>() {

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val storyImages: ViewPager2 = itemView.findViewById(R.id.storyImages)
        val storyImageIndicator: TabLayout = itemView.findViewById(R.id.storyImageIndicator)
        val storyTitle: TextView = itemView.findViewById(R.id.storyTitle)
        val storyAuthor: TextView = itemView.findViewById(R.id.storyAuthor)
//        val storyPublishDate: TextView = itemView.findViewById(R.id.storyPublishDate)
        val storyAbstract: TextView = itemView.findViewById(R.id.storyAbstract)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {

        return StoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_story, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {

        val item = stories[position]

        if (!item.multimedia.isNullOrEmpty()) {

            initImageSlider(holder.storyImages, holder.storyImageIndicator, item.multimedia)
        }


        holder.storyTitle.text = item.title
        holder.storyAuthor.text = item.byline
//        holder.storyPublishDate.text = item.published_date
        holder.storyAbstract.text = item.abstract
    }

    override fun getItemCount(): Int = stories.size

    private fun initImageSlider(storyImages: ViewPager2, indicator: TabLayout, images: List<StoriesResponseModel.Result.Multimedia>) {

        val pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(40))

        storyImages.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(pageTransformer)
            adapter = StoryImagesAdapter(context, images)
        }

        TabLayoutMediator(indicator, storyImages) { _, _ -> }.attach()

    }

    fun setItmes(items: List<StoriesResponseModel.Result>) {

        stories = items
        notifyItemRangeChanged(0, stories.size)
    }
}