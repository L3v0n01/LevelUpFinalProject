package com.adobe.finalProject.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class BookAdapter(
    private val onClick: (url: String) -> Unit,
    private val context: Fragment,
    private var books: BooksResponseModel?
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<AppCompatImageView>(R.id.bookimage)
        val titleTv = itemView.findViewById<AppCompatTextView>(R.id.bookName)
        val author = itemView.findViewById<AppCompatTextView>(R.id.authorInfo)
        val section = itemView.findViewById<AppCompatTextView>(R.id.section)
        val publishDate = itemView.findViewById<AppCompatTextView>(R.id.published_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.titleTv.text = books?.results?.get(position)?.title
        holder.publishDate.text = books?.results?.get(position)?.published_date
        holder.author.text = books?.results?.get(position)?.byline
        holder.section.text = books?.results?.get(position)?.section
        holder.itemView.setOnClickListener {
            onClick.invoke(books?.results?.get(position)?.url ?: "")
        }
        if (books?.results?.get(position)?.media.isNullOrEmpty().not()) {
            Glide
                .with(context)
                .load(books?.results?.get(position)?.media?.get(0)?.mediametadata?.get(2)?.url)
                .transform(CenterCrop(), RoundedCorners(20))
                .placeholder(R.drawable.ic_newspaper_outline)
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int = books?.results?.size ?: 0

    fun setbook(items: BooksResponseModel?) {

        books = items
        notifyItemRangeChanged(0, books?.results?.size ?: 0)
    }
}

