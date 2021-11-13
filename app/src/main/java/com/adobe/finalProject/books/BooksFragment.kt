package com.adobe.finalProject.books

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R
import com.adobe.finalProject.utils.Constants.API_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : Fragment(R.layout.fragment_book) {

    private val booksViewModel: BooksViewModel by viewModel()
    private val adapter =BookAdapter(this,BooksResponseModel())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView=view.findViewById<RecyclerView>(R.id.rvBook)
        recyclerView.adapter=adapter
        setupObservers()
        booksViewModel.getBooksList(API_KEY)
    }

    private fun setupObservers() {
        booksViewModel.loadingLiveData.observe(viewLifecycleOwner,{
            // Cuyc tal loading
            Log.i("ABC", it.toString())
        })
        booksViewModel.failLiveData.observe(viewLifecycleOwner,{
            // Cuyc tal toast
            Log.i("ABC", it)

        })
        booksViewModel.booksListLiveData.observe(viewLifecycleOwner, {

            adapter.setbook(it)
            //nkarel ekac datan recycler view-i mej
        })
    }




}