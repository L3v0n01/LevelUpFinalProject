package com.adobe.finalProject.books

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.adobe.finalProject.R
import com.adobe.finalProject.utils.Constants.API_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : Fragment(R.layout.fragment_view_1) {

    private val booksViewModel: BooksViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            Log.i("ABC", it.bestsellers_date)
            //nkarel ekac datan recycler view-i mej
        })
    }


}