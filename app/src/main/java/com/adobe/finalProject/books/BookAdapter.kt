package com.adobe.finalProject.books

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.runtime.toMutableStateList
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R
import com.adobe.finalProject.stories.StoriesResponseModel
import com.bumptech.glide.Glide

class BookAdapter(
    private val context: Fragment,
    private var books: BooksResponseModel?
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookClickStory: LinearLayoutCompat = itemView.findViewById(R.id.book_story)
        val sourceid: AppCompatTextView = itemView.findViewById(R.id.source_id)
        val published_dateid: AppCompatTextView = itemView.findViewById(R.id.published_date_id)
        val adx_keywords_id: AppCompatTextView = itemView.findViewById(R.id.adx_keywords_id)
        val title: AppCompatTextView = itemView.findViewById(R.id.title_id)
        val abstract: AppCompatTextView = itemView.findViewById(R.id.abstract_id)
        val ImageUrl: AppCompatImageView = itemView.findViewById(R.id.image)

//        fun setImageView1(image: BooksResponseModel.Result.Media.MediaMetadata, context: Context) {
//
////            Glide.with(context)
////                .load(image.url)
////                .centerCrop()
////                .placeholder(R.drawable.ic_newspaper_outline)
////                .into(ImageUrl)
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.sourceid.text = books?.results?.get(position)?.source
        holder.published_dateid.text = books?.results?.get(position)?.published_date
        holder.adx_keywords_id.text = books?.results?.get(position)?.adx_keywords
        holder.title.text = books?.results?.get(position)?.adx_keywords
        holder.abstract.text = books?.results?.get(position)?.abstract




        Glide
            .with(context)
            .load(books?.results?.get(position)?.media?.get(position)?.mediametadata?.get(position))
            .centerCrop()
            .placeholder(R.drawable.ic_newspaper_outline)
            .into(holder.ImageUrl)

    }

    override fun getItemCount(): Int = books?.results?.size?:0

    fun setbook(items: BooksResponseModel?) {

        books = items
        notifyItemRangeChanged(0, books?.results?.size?:0)
    }
}

