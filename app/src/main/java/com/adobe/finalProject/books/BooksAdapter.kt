package com.adobe.finalProject.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R

class BooksAdapter(private val books: MutableList<BooksResponseModel.Results.Lists.Book>) :
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
        holder.bestSellersDate.text = books[position].author
        holder.nextPublishedDate.text = books[position].title
    }

    override fun getItemCount() = books.size

    fun updateList(list:List<BooksResponseModel.Results.Lists.Book>){
        books.clear()
        books.addAll(list)
        notifyDataSetChanged()
    }
}

