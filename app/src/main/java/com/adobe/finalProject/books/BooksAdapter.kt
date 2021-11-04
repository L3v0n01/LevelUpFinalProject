package com.adobe.finalProject.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R

class BooksAdapter(

    private val books: List<BooksResponseModel.Results>,


) :
    RecyclerView.Adapter<BooksAdapter.BooksViewholder>() {

    class BooksViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bestSellersDate: AppCompatTextView = itemView.findViewById(R.id.currencyToGet)

        // val lists:TextView = itemView.findViewById(R.id.textView)
        val nextPublishedDate: TextView = itemView.findViewById(R.id.textView2)
        val previousPublishedDate: TextView = itemView.findViewById(R.id.textView3)
        val publishedDate: TextView = itemView.findViewById(R.id.textView4)
        val publishedDateDescription: TextView = itemView.findViewById(R.id.textView5)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewholder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_first, parent, false)
        return BooksViewholder(view)
    }

    override fun onBindViewHolder(holder: BooksViewholder, position: Int) {
        holder.bestSellersDate.text = books[position].bestsellers_date
        holder.nextPublishedDate.text = books[position].next_published_date
        holder.previousPublishedDate.text = books[position].previous_published_date
        holder.publishedDate.text = books[position].published_date
        holder.publishedDateDescription.text = books[position].published_date_description

    }

    override fun getItemCount() = books.size
}

